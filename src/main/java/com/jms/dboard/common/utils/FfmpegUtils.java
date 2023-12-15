package com.jms.dboard.common.utils;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;


@Component
public class FfmpegUtils {

	@Value("${ffprobe.path}")
	private String ffprobePath;
	
	@Value("${ffmpeg.path}")
	private String ffmpegPath;
	
    private FFprobe ffprobe;
    
    private FFmpeg ffmpeg;
    
//	@PostConstruct
    public void init(){
        try {
        	String ffprobeRealPath = getClass().getResource(ffprobePath).getPath();
        	String ffmpegRealPath = getClass().getResource(ffmpegPath).getPath();
        	System.out.println("ffmpeg : "+ ffmpegRealPath);
            ffprobe = new FFprobe(ffprobeRealPath);
            ffmpeg = new FFmpeg(ffmpegRealPath);

            System.out.println("FFmpeg & FFprobe init complete.");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
	
	public  Map<String,Object> getVideoResolution(String filePath){
		Map<String,Object> mediaInfo = new HashMap<String,Object>();
		try {
			System.out.println(ffprobePath);

			FFmpegProbeResult probeResult = ffprobe.probe(filePath);

			FFmpegFormat format = probeResult.getFormat();
//			System.out.format("%nFile: '%s' ; Format: '%s' ; Duration: %.3fs", 
//					format.filename, 
//					format.format_long_name,
//					format.duration
//					);

			FFmpegStream stream = probeResult.getStreams().get(0);
//			System.out.format("%nCodec: '%s' ; Width: %dpx ; Height: %dpx",
//					stream.codec_long_name,
//					stream.width,
//					stream.height
//					);
			
			mediaInfo.put("duration", format.duration);
			mediaInfo.put("codec", stream.codec_long_name);
			mediaInfo.put("width", stream.width);
			mediaInfo.put("height", stream.height);
		}
		catch(Exception e) {
			mediaInfo.put("duration", 0);
			mediaInfo.put("codec", "");
			mediaInfo.put("width", 0);
			mediaInfo.put("height", 0);
			e.printStackTrace();
		}
		return mediaInfo;
	}
	
	public String convertVideo(File newFile, String tmpFileName, String scaleSize) {
		String resultFile = "";
		System.out.println(newFile.getParentFile().getAbsolutePath()+File.separator+tmpFileName);
		System.out.println(newFile.getAbsolutePath());
		try {
			
			FFmpegBuilder builder = new FFmpegBuilder().setInput(newFile.getAbsolutePath()) // 동영상 파일경로
					.overrideOutputFiles(true) // 오버라이드
					.addOutput(newFile.getParentFile().getAbsolutePath()+File.separator+tmpFileName) // 변환 후 저장할 경로
					.setFormat("mp4") // 포맷 ( 확장자 )
					.setVideoCodec("libvpx-vp9") // 비디오 코덱
					.disableSubtitle() // 서브타이틀 제거
					.setAudioChannels(2) // 오디오 채널 ( 1 : 모노 , 2 : 스테레오 )
//					.setVideoResolution(1080, 1920) // 동영상 해상도
//					.setVideoBitRate(1464800) // 비디오 비트레이트 
					.setVideoBitRate(4000000) // 비디오 비트레이트
					.setAudioCodec("libopus")
					.setVideoFilter("scale=\""+scaleSize+",setsar=1/1\"")
					.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // ffmpeg 빌더 실행 허용
					.done();
			
			FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
			executor.createJob(builder).run();
			resultFile = newFile.getParentFile().getAbsolutePath()+File.separator+tmpFileName;
			//Runtime.getRuntime().exec("C:\\Dev\\D-Board_YDP\\src\\main\\resources\\ffmpeg\\ffmpeg -i "+newFile.getAbsolutePath()+"  -vf \"scale=1920:1080,setsar=1/1\" "+newFile.getParentFile().getAbsolutePath()+File.separator+tmpFileName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return resultFile;
	}
}