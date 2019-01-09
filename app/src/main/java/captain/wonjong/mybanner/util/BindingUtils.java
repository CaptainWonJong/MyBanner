package captain.wonjong.mybanner.util;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import captain.wonjong.mybanner.R;
import captain.wonjong.mybanner.main.adapter.BannerAdapter;
import captain.wonjong.mybanner.util.AutoScrollViewPager;

public class BindingUtils {

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter({"bind:setImage"})
    public static void setImage(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            if ('#' == url.charAt(0)) {
                imageView.setBackgroundColor(Color.parseColor(url));
            } else {
                Glide.with(imageView.getContext()).load(url).into(imageView);
            }
        }
    }

    @BindingAdapter({"bind:setImageAnim"})
    public static void setImageAnim(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            if ('#' == url.charAt(0)) {
                imageView.setBackgroundColor(Color.parseColor(url));
            } else {
                Glide.with(imageView.getContext()).load(url).into(imageView);
                Animation anim = AnimationUtils.loadAnimation(imageView.getContext(), R.anim.alpha_on);
                imageView.setAnimation(anim);
            }
        }
    }

    @BindingAdapter({"bind:setImage"})
    public static void setImage(ImageView imageView, @DrawableRes int url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter({"bind:setAnimationImage"})
    public static void setAnimationImage(ImageView imageView, @DrawableRes int url) {
        try {
            if (imageView != null) {
                imageView.setImageResource(url);
                AnimationDrawable frameAnimation = (AnimationDrawable) imageView.getDrawable();
                frameAnimation.start();
            }
        } catch (Exception e) {
        }
    }

    @BindingAdapter({"bind:indeterminateDrawable"})
    public static void setAnimaion(ProgressBar progressBar, @DrawableRes int url) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                progressBar.setIndeterminateDrawable(progressBar.getContext().getDrawable(url));
            } else {
                progressBar.setBackgroundResource(url);
                progressBar.setIndeterminateDrawable(null);
            }
        } catch (Exception e) {
            progressBar.setIndeterminateDrawable(null);
        }
    }

    @BindingAdapter({"bind:setBackgroundColor"})
    public static void setBackgroundColor(ViewGroup viewGroup, String bg) {
        if (!TextUtils.isEmpty(bg)) {
            if ('#' == bg.charAt(0)) {
                viewGroup.setBackgroundColor(Color.parseColor(bg));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewGroup.setBackgroundColor(viewGroup.getContext().getColor(android.R.color.transparent));
            }
        }
    }

    @BindingAdapter({"bind:setBackgroundColor"})
    public static void setBackgroundColor(View view, String bg) {
        if (!TextUtils.isEmpty(bg)) {
            if ('#' == bg.charAt(0)) {
                view.setBackgroundColor(Color.parseColor(bg));
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setBackgroundColor(view.getContext().getColor(android.R.color.transparent));
            }
        }
    }

    @BindingAdapter({"bind:htmlText"})
    public static void setHtmlText(TextView textView, String text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(Html.fromHtml(text));
        } else {
            textView.setText("");
        }
    }

    @BindingAdapter({"bind:interval"})
    public static void setInterval(AutoScrollViewPager asvp, int interval) {
        asvp.setInterval(interval);
    }

    @BindingAdapter({"bind:onPageChangeListener"})
    public static void addOnPageChangeListener(AutoScrollViewPager asvp, AutoScrollViewPager.OnPageChangeListener onPageChangeListener) {
        asvp.addOnPageChangeListener(onPageChangeListener);
    }

    @BindingAdapter({"bind:setAutoScroll"})
    public static void setAutoScroll(AutoScrollViewPager asvp, boolean b) {
        if (b) {
            asvp.startAutoScroll();
        }
    }

}
