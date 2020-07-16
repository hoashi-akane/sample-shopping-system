package service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class GoodsService {

//	商品のファイル一覧取得
	public File[] getImageFileList(String imageDir){
		File file = new File(imageDir);
		File[] fileList = file.listFiles();
		return fileList;
	}
	
	
//	商品のファイルの一番最初のこんてんつを返すメソッド
	public File findImageFile(String imageDir) {
		File file = new File(imageDir);
		return file.listFiles()[0];
	}
	
//	画像を出力するサービス
	public void outputImages(String imagePath, HttpServletResponse response) {
		// TODO 自動生成されたメソッド・スタブ
		boolean isSuccess = false;
		ServletOutputStream out = null;
		BufferedInputStream in = null;
		
		int data = 0;
		try {
			out = response.getOutputStream();
			
			in = new BufferedInputStream(new FileInputStream(imagePath));
			
			while((data = in.read()) != -1) {
				out.write(data);
			}
			
		}catch(Exception e) {
			
		}finally {
			if( in != null )
				try {
					in.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			if( out != null )
				try {
					out.close();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
		}
	}
	
//ervletOutputStream out = response.getOutputStream();
//	BufferedInputStream in = new BufferedInputStream(new FileInputStream(goodsDto.getImageDir()));
}
