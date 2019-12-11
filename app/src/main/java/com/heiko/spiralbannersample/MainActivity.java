package com.heiko.spiralbannersample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.heiko.spiralbanner.SpiralAdapter;
import com.heiko.spiralbanner.SpiralBanner;
import com.heiko.spiralbanner.transformer.LoopTransformer;

import java.util.ArrayList;
import java.util.List;

//详见 https://blog.csdn.net/smile_running/article/details/81078939
public class MainActivity extends AppCompatActivity {
    private SpiralBanner mSpiralBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<MyData> dataList = new ArrayList<>();
        dataList.add(new MyData("title1", "http://dmimg.5054399.com/allimg/pkm/pk/22.jpg"));
        dataList.add(new MyData("title2", "http://file02.16sucai.com/d/file/2014/0704/e53c868ee9e8e7b28c424b56afe2066d.jpg"));
        dataList.add(new MyData("title3", "http://file02.16sucai.com/d/file/2014/0829/372edfeb74c3119b666237bd4af92be5.jpg"));
        dataList.add(new MyData("title4", "http://file02.16sucai.com/d/file/2014/1124/68d1ffe81ad8f4fc84d580be7b556521.jpg"));
        dataList.add(new MyData("title5", "http://file02.16sucai.com/d/file/2014/0822/b44cd1310d09026f6dd1f0633a1cc2a5.jpg"));
        dataList.add(new MyData("title6", "http://file02.16sucai.com/d/file/2014/1027/b89895562653560201301b412ae1c8d6.jpg"));

        mSpiralBanner = findViewById(R.id.spiral_banner);
        mSpiralBanner.setAdapter(new SpiralAdapter<MyData>(this, R.layout.item_view, dataList) {

            @Override
            protected void convert(View view, MyData myData) {
                ImageView image = view.findViewById(R.id.image);
                TextView tv = view.findViewById(R.id.tv);
                tv.setText(myData.getTitle());
                Glide.with(MainActivity.this).load(myData.getImage()).into(image);
            }
        });
        mSpiralBanner.setPageTransformer(false, new LoopTransformer());


        Button btnShowDialog = findViewById(R.id.btn_show_dialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}