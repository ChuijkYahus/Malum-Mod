package com.sammy.malum.client.model.mob;

import com.sammy.malum.client.model.CachedModelPart;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Vector3f;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class HierarchicalHumanoidModel<T extends LivingEntity> extends HumanoidModel<T> {

    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    protected final ModelPart root;
    protected final CachedModelPart cachedHead;
    protected final CachedModelPart cachedBody;
    protected final CachedModelPart cachedRightArm;
    protected final CachedModelPart cachedLeftArm;
    protected final CachedModelPart cachedRightLeg;
    protected final CachedModelPart cachedLeftLeg;

    public HierarchicalHumanoidModel(ModelPart root) {
        super(root);
        this.root = root;
        this.cachedHead = CachedModelPart.of(head);
        this.cachedBody = CachedModelPart.of(body);
        this.cachedRightArm = CachedModelPart.of(rightArm);
        this.cachedLeftArm = CachedModelPart.of(leftArm);
        this.cachedRightLeg = CachedModelPart.of(rightLeg);
        this.cachedLeftLeg = CachedModelPart.of(leftLeg);
    }

    public Optional<ModelPart> getAnyDescendantWithName(String name) {
        return name.equals("root")
                ? Optional.of(root)
                : root.getAllParts().filter(p_233400_ -> p_233400_.hasChild(name)).findFirst().map(p_233397_ -> p_233397_.getChild(name));
    }


    protected void animate(AnimationState animationState, AnimationDefinition animationDefinition, float ageInTicks) {
        this.animate(animationState, animationDefinition, ageInTicks, 1.0F);
    }

    protected void animate(AnimationState animationState, net.neoforged.neoforge.client.entity.animation.json.AnimationHolder animation, float ageInTicks) {
        this.animate(animationState, animation.get(), ageInTicks);
    }

    protected void animateWalk(AnimationDefinition animationDefinition, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor) {
        long i = (long) (limbSwing * 50.0F * maxAnimationSpeed);
        float f = Math.min(limbSwingAmount * animationScaleFactor, 1.0F);
        animate(this, animationDefinition, i, f, ANIMATION_VECTOR_CACHE);
    }

    protected void animateWalk(net.neoforged.neoforge.client.entity.animation.json.AnimationHolder animation, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor) {
        this.animateWalk(animation.get(), limbSwing, limbSwingAmount, maxAnimationSpeed, animationScaleFactor);
    }

    protected void animate(AnimationState animationState, AnimationDefinition animationDefinition, float ageInTicks, float speed) {
        animationState.updateTime(ageInTicks, speed);
        animationState.ifStarted(time -> animate(this, animationDefinition, time.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE));
    }

    protected void applyStatic(AnimationDefinition animationDefinition) {
        animate(this, animationDefinition, 0L, 1.0F, ANIMATION_VECTOR_CACHE);
    }

    public static void animate(HierarchicalHumanoidModel<?> model, AnimationDefinition animationDefinition, long accumulatedTime, float scale, Vector3f animationVecCache) {
        float f = getElapsedSeconds(animationDefinition, accumulatedTime);

        for (Map.Entry<String, List<AnimationChannel>> entry : animationDefinition.boneAnimations().entrySet()) {
            Optional<ModelPart> optional = model.getAnyDescendantWithName(entry.getKey());
            List<AnimationChannel> list = entry.getValue();
            optional.ifPresent(p_232330_ -> list.forEach(p_288241_ -> {
                Keyframe[] akeyframe = p_288241_.keyframes();
                int i = Math.max(0, Mth.binarySearch(0, akeyframe.length, p_232315_ -> f <= akeyframe[p_232315_].timestamp()) - 1);
                int j = Math.min(akeyframe.length - 1, i + 1);
                Keyframe keyframe = akeyframe[i];
                Keyframe keyframe1 = akeyframe[j];
                float f1 = f - keyframe.timestamp();
                float f2;
                if (j != i) {
                    f2 = Mth.clamp(f1 / (keyframe1.timestamp() - keyframe.timestamp()), 0.0F, 1.0F);
                } else {
                    f2 = 0.0F;
                }

                keyframe1.interpolation().apply(animationVecCache, f2, akeyframe, i, j, scale);
                p_288241_.target().apply(p_232330_, animationVecCache);
            }));
        }
    }

    private static float getElapsedSeconds(AnimationDefinition animationDefinition, long accumulatedTime) {
        float f = (float)accumulatedTime / 1000.0F;
        return animationDefinition.looping() ? f % animationDefinition.lengthInSeconds() : f;
    }

    public static Vector3f posVec(float x, float y, float z) {
        return new Vector3f(x, -y, z);
    }

    public static Vector3f degreeVec(float xDegrees, float yDegrees, float zDegrees) {
        return new Vector3f(xDegrees * (float) (Math.PI / 180.0), yDegrees * (float) (Math.PI / 180.0), zDegrees * (float) (Math.PI / 180.0));
    }

    public static Vector3f scaleVec(double xScale, double yScale, double zScale) {
        return new Vector3f((float)(xScale - 1.0), (float)(yScale - 1.0), (float)(zScale - 1.0));
    }
}