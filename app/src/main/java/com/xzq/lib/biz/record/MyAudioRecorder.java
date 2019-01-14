package com.xzq.lib.biz.record;

import android.media.MediaRecorder;
import android.os.Environment;
import android.text.format.DateFormat;

import com.cjt2325.cameralibrary.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * MyAudioRecorder
 * Created by Tse on 2019/1/14.
 */

public class MyAudioRecorder {

    private static MyAudioRecorder recorder = new MyAudioRecorder();

    public static MyAudioRecorder getRecorder() {
        return recorder;
    }

    private MediaRecorder mMediaRecorder;
    private String fileName;
    private String filePath;
    private String audioSaveDir;

    public void startRecord() {
        // 开始录音
    /* ①Initial：实例化MediaRecorder对象 */
        if (mMediaRecorder == null)
            mMediaRecorder = new MediaRecorder();
        try {
        /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
        /*
         * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
         * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
         */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);
        /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
            fileName = DateFormat.format("yyyyMMdd_HHmmss", Calendar.getInstance(Locale.CHINA)) + ".amr";
            audioSaveDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "DCIM/Camera";
            File file = new File(audioSaveDir);
            if (!file.exists()) {
                file.mkdirs();
            }
            filePath = audioSaveDir + fileName;
        /* ③准备 */
            mMediaRecorder.setOutputFile(filePath);
            mMediaRecorder.prepare();
        /* ④开始 */
            mMediaRecorder.start();
        } catch (IllegalStateException e) {
            LogUtil.i("call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        } catch (IOException e) {
            LogUtil.i("call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        }
    }

    public void stopRecord() {
        if (mMediaRecorder==null){return;}
        try {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
            filePath = "";
        } catch (RuntimeException e) {
            LogUtil.e(e.toString());
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;

            File file = new File(filePath);
            if (file.exists())
                file.delete();

            filePath = "";
        }
    }
}
