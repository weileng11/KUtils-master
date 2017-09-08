
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.zwy.kutils.animations;

import com.zwy.kutils.animations.attention.BounceAnimator;
import com.zwy.kutils.animations.attention.FlashAnimator;
import com.zwy.kutils.animations.attention.PulseAnimator;
import com.zwy.kutils.animations.attention.RubberBandAnimator;
import com.zwy.kutils.animations.attention.ShakeAnimator;
import com.zwy.kutils.animations.attention.StandUpAnimator;
import com.zwy.kutils.animations.attention.SwingAnimator;
import com.zwy.kutils.animations.attention.TadaAnimator;
import com.zwy.kutils.animations.attention.WaveAnimator;
import com.zwy.kutils.animations.attention.WobbleAnimator;
import com.zwy.kutils.animations.bouncing_entrances.BounceInAnimator;
import com.zwy.kutils.animations.bouncing_entrances.BounceInDownAnimator;
import com.zwy.kutils.animations.bouncing_entrances.BounceInLeftAnimator;
import com.zwy.kutils.animations.bouncing_entrances.BounceInRightAnimator;
import com.zwy.kutils.animations.bouncing_entrances.BounceInUpAnimator;
import com.zwy.kutils.animations.fading_entrances.FadeInAnimator;
import com.zwy.kutils.animations.fading_entrances.FadeInDownAnimator;
import com.zwy.kutils.animations.fading_entrances.FadeInLeftAnimator;
import com.zwy.kutils.animations.fading_entrances.FadeInRightAnimator;
import com.zwy.kutils.animations.fading_entrances.FadeInUpAnimator;
import com.zwy.kutils.animations.fading_exits.FadeOutAnimator;
import com.zwy.kutils.animations.fading_exits.FadeOutDownAnimator;
import com.zwy.kutils.animations.fading_exits.FadeOutLeftAnimator;
import com.zwy.kutils.animations.fading_exits.FadeOutRightAnimator;
import com.zwy.kutils.animations.fading_exits.FadeOutUpAnimator;
import com.zwy.kutils.animations.flippers.FlipInXAnimator;
import com.zwy.kutils.animations.flippers.FlipInYAnimator;
import com.zwy.kutils.animations.flippers.FlipOutXAnimator;
import com.zwy.kutils.animations.flippers.FlipOutYAnimator;
import com.zwy.kutils.animations.rotating_entrances.RotateInAnimator;
import com.zwy.kutils.animations.rotating_entrances.RotateInDownLeftAnimator;
import com.zwy.kutils.animations.rotating_entrances.RotateInDownRightAnimator;
import com.zwy.kutils.animations.rotating_entrances.RotateInUpLeftAnimator;
import com.zwy.kutils.animations.rotating_entrances.RotateInUpRightAnimator;
import com.zwy.kutils.animations.rotating_exits.RotateOutAnimator;
import com.zwy.kutils.animations.rotating_exits.RotateOutDownLeftAnimator;
import com.zwy.kutils.animations.rotating_exits.RotateOutDownRightAnimator;
import com.zwy.kutils.animations.rotating_exits.RotateOutUpLeftAnimator;
import com.zwy.kutils.animations.rotating_exits.RotateOutUpRightAnimator;
import com.zwy.kutils.animations.sliders.SlideInDownAnimator;
import com.zwy.kutils.animations.sliders.SlideInLeftAnimator;
import com.zwy.kutils.animations.sliders.SlideInRightAnimator;
import com.zwy.kutils.animations.sliders.SlideInUpAnimator;
import com.zwy.kutils.animations.sliders.SlideOutDownAnimator;
import com.zwy.kutils.animations.sliders.SlideOutLeftAnimator;
import com.zwy.kutils.animations.sliders.SlideOutRightAnimator;
import com.zwy.kutils.animations.sliders.SlideOutUpAnimator;
import com.zwy.kutils.animations.specials.HingeAnimator;
import com.zwy.kutils.animations.specials.RollInAnimator;
import com.zwy.kutils.animations.specials.RollOutAnimator;
import com.zwy.kutils.animations.specials.in.DropOutAnimator;
import com.zwy.kutils.animations.specials.in.LandingAnimator;
import com.zwy.kutils.animations.specials.out.TakingOffAnimator;
import com.zwy.kutils.animations.zooming_entrances.ZoomInAnimator;
import com.zwy.kutils.animations.zooming_entrances.ZoomInDownAnimator;
import com.zwy.kutils.animations.zooming_entrances.ZoomInLeftAnimator;
import com.zwy.kutils.animations.zooming_entrances.ZoomInRightAnimator;
import com.zwy.kutils.animations.zooming_entrances.ZoomInUpAnimator;
import com.zwy.kutils.animations.zooming_exits.ZoomOutAnimator;
import com.zwy.kutils.animations.zooming_exits.ZoomOutDownAnimator;
import com.zwy.kutils.animations.zooming_exits.ZoomOutLeftAnimator;
import com.zwy.kutils.animations.zooming_exits.ZoomOutRightAnimator;
import com.zwy.kutils.animations.zooming_exits.ZoomOutUpAnimator;

public enum Techniques {

    DropOut(DropOutAnimator.class),
    Landing(LandingAnimator.class),
    TakingOff(TakingOffAnimator.class),

    Flash(FlashAnimator.class),
    Pulse(PulseAnimator.class),
    RubberBand(RubberBandAnimator.class),
    Shake(ShakeAnimator.class),
    Swing(SwingAnimator.class),
    Wobble(WobbleAnimator.class),
    Bounce(BounceAnimator.class),
    Tada(TadaAnimator.class),
    StandUp(StandUpAnimator.class),
    Wave(WaveAnimator.class),

    Hinge(HingeAnimator.class),
    RollIn(RollInAnimator.class),
    RollOut(RollOutAnimator.class),

    BounceIn(BounceInAnimator.class),
    BounceInDown(BounceInDownAnimator.class),
    BounceInLeft(BounceInLeftAnimator.class),
    BounceInRight(BounceInRightAnimator.class),
    BounceInUp(BounceInUpAnimator.class),

    FadeIn(FadeInAnimator.class),
    FadeInUp(FadeInUpAnimator.class),
    FadeInDown(FadeInDownAnimator.class),
    FadeInLeft(FadeInLeftAnimator.class),
    FadeInRight(FadeInRightAnimator.class),

    FadeOut(FadeOutAnimator.class),
    FadeOutDown(FadeOutDownAnimator.class),
    FadeOutLeft(FadeOutLeftAnimator.class),
    FadeOutRight(FadeOutRightAnimator.class),
    FadeOutUp(FadeOutUpAnimator.class),

    FlipInX(FlipInXAnimator.class),
    FlipOutX(FlipOutXAnimator.class),
    FlipInY(FlipInYAnimator.class),
    FlipOutY(FlipOutYAnimator.class),
    RotateIn(RotateInAnimator.class),
    RotateInDownLeft(RotateInDownLeftAnimator.class),
    RotateInDownRight(RotateInDownRightAnimator.class),
    RotateInUpLeft(RotateInUpLeftAnimator.class),
    RotateInUpRight(RotateInUpRightAnimator.class),

    RotateOut(RotateOutAnimator.class),
    RotateOutDownLeft(RotateOutDownLeftAnimator.class),
    RotateOutDownRight(RotateOutDownRightAnimator.class),
    RotateOutUpLeft(RotateOutUpLeftAnimator.class),
    RotateOutUpRight(RotateOutUpRightAnimator.class),

    SlideInLeft(SlideInLeftAnimator.class),
    SlideInRight(SlideInRightAnimator.class),
    SlideInUp(SlideInUpAnimator.class),
    SlideInDown(SlideInDownAnimator.class),

    SlideOutLeft(SlideOutLeftAnimator.class),
    SlideOutRight(SlideOutRightAnimator.class),
    SlideOutUp(SlideOutUpAnimator.class),
    SlideOutDown(SlideOutDownAnimator.class),

    ZoomIn(ZoomInAnimator.class),
    ZoomInDown(ZoomInDownAnimator.class),
    ZoomInLeft(ZoomInLeftAnimator.class),
    ZoomInRight(ZoomInRightAnimator.class),
    ZoomInUp(ZoomInUpAnimator.class),

    ZoomOut(ZoomOutAnimator.class),
    ZoomOutDown(ZoomOutDownAnimator.class),
    ZoomOutLeft(ZoomOutLeftAnimator.class),
    ZoomOutRight(ZoomOutRightAnimator.class),
    ZoomOutUp(ZoomOutUpAnimator.class);



    private Class animatorClazz;

    private Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public BaseViewAnimator getAnimator() {
        try {
            return (BaseViewAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
