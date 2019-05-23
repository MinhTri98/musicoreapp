package com.example.splashscreen;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class playSongs extends AppCompatActivity {

    TextView txtTitle, txtSinger,txtTimeSong, txtTimeTotal;
    SeekBar skSong;
    ImageButton btnPrev, btnPlay, btnStop, btnNext, btnBack;
    ArrayList<song> arrSong;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;
    ImageView imgCD;

    private void updateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                //cập nhật thanh seekbar
                skSong.setProgress(mediaPlayer.getCurrentPosition());
                //nếu kết thúc -> next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arrSong.size() - 1){
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        newMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        setTimeTotal();
                        updateTimeSong();
                    }
                });

                handler.postDelayed(this,500);
            }
        },100);
    }

    private void setTimeTotal(){
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        skSong.setMax(mediaPlayer.getDuration());
    }

    private void newMediaPlayer(){
        mediaPlayer = MediaPlayer.create(playSongs.this, arrSong.get(position).getFile());
        txtTitle.setText(arrSong.get(position).getTitle());
        txtSinger.setText(arrSong.get(position).getSinger());
    }

    private void addSong() {
        arrSong = new ArrayList<>();
        arrSong.add(new song("Một Bước Yêu Vạn Dặm Đau","Mr.Siro", R.raw.motbuocyeuvandamdau));
        arrSong.add(new song("Hồng Nhan","Jack", R.raw.hongnhan));
        arrSong.add(new song("Anh Đang Ở Đâu Đấy Anh","Hương Giang", R.raw.anhdangodaudayanh));
        arrSong.add(new song("Về Đây Em Lo","Ái Vy", R.raw.vedayemlo));
        arrSong.add(new song("Đời Là Thế Thôi","Phú Lê", R.raw.doilathethoi));
        arrSong.add(new song("24H","Ly Ly", R.raw.haitugio));
    }

    private void anhXa() {
        txtTimeSong = (TextView) findViewById(R.id.textViewTimeSong);
        txtTimeTotal = (TextView) findViewById(R.id.textViewTimeTotal);
        txtTitle = (TextView) findViewById(R.id.textViewTitle);
        txtSinger = (TextView) findViewById(R.id.textViewSinger);
        skSong = (SeekBar) findViewById(R.id.seekBarSong);
        btnPrev = (ImageButton) findViewById(R.id.buttonPrev);
        btnPlay = (ImageButton) findViewById(R.id.buttonPlay);
        btnStop = (ImageButton) findViewById(R.id.buttonStop);
        btnNext = (ImageButton) findViewById(R.id.buttonNext);
        imgCD = (ImageView) findViewById(R.id.imageCD);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playsongs);

        anhXa();
        addSong();
        animation= AnimationUtils.loadAnimation(this,R.anim.cd_rotate);
        newMediaPlayer();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arrSong.size() - 1){
                    position = 0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                newMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimeTotal();
                updateTimeSong();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0){
                    position = arrSong.size() - 1;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                newMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimeTotal();
                updateTimeSong();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.playbtn);
                newMediaPlayer();
                
            }
        });

        btnPlay.setOnClickListener((view) -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnPlay.setImageResource(R.drawable.playbtn);
            } else {
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
            }
            setTimeTotal();
            updateTimeSong();
            imgCD.startAnimation(animation);
        });
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }

    private void doBack() {
        Intent intent = new Intent(this,tab.class);
        startActivity(intent);
    }
}

