package captain.wonjong.mybanner.main.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import captain.wonjong.mybanner.R;
import captain.wonjong.mybanner.main.BannerModel;
import captain.wonjong.mybanner.util.RPKey;

public class BannerAdapter extends PagerAdapter{
    private Context mContext;
    private OnItemClickListener mItemClickListener = null;
    public MutableLiveData<ArrayList<HashMap<String, Object>>> bnrList;
    private LayoutInflater m_layoutInflater = null;

    private int m_nTotalCount = 0;		// 가짜 페이지 포함 갯수
    private int m_nImageNumber = 0;		// 가짜 페이지 2개가 존재하기에 가짜 페이지 제외한 실제  이미지 번호

    public BannerAdapter(Context context, MutableLiveData<ArrayList<HashMap<String, Object>>> bannerList) {
        mContext = context;
        bnrList = bannerList;
        this.m_nTotalCount = (bnrList.getValue().size() > 1)? (bnrList.getValue().size() + 2) : bnrList.getValue().size();
        this.m_layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return m_nTotalCount;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup pager, final int position) {
        View view = null;
        view = m_layoutInflater.inflate(R.layout.layout_banner_item, null);

        ImageView ivBannerImage = (ImageView) view.findViewById(R.id.iv_image);
        TextView tvBannerText = (TextView) view.findViewById(R.id.tv_text);
        ivBannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick((m_nTotalCount > 1)? (position - 1) : position);
            }
        });

        /**
         * 예) 6장 + 가짜 2장 = 총 8장
         *
         * 페이지 번호		실제 이미지 번호
         * 0			      5
         * 1				  0
         * 2				  1
         * 3				  2
         * 4				  3
         * 5				  4
         * 6				  5
         * 7				  0
         */
        // 이미지가 2장 이상일 경우 auto scroll 적용
        if (m_nTotalCount > 1) {
            if (position == 0) {
                m_nImageNumber = m_nTotalCount - 3;
            } else {
                if (position == (m_nTotalCount - 1)) {
                    m_nImageNumber = 0;
                } else {
                    m_nImageNumber = position - 1;
                }
            }
        } else {
            m_nImageNumber = position;
        }

        Glide.with(mContext).load(bnrList.getValue().get(m_nImageNumber).get(RPKey.MAIN_IMAGE)).into(ivBannerImage);
        tvBannerText.setText(bnrList.getValue().get(m_nImageNumber).get(RPKey.TITLE).toString());

        pager.addView(view);
        return view;
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup pager, int position, Object view) {
        pager.removeView((View) view);
    }

    public static interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
