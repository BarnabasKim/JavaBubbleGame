package bubble.test.ex07;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 메인 스레드 바쁨 - 키보드 이벤트 처리
// 백그라운드에서 계속 관찰
public class BackgroundPlayerService implements Runnable{

	private BufferedImage image;
	private Player player;
	
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void run() {
		while(true){
			// 색상 확인
			Color leftcolor = new Color(image.getRGB(player.getX() -10 , player.getY()+ 25));
			Color rightcolor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY()+ 25));

			
			if(leftcolor.getRed() == 255 && leftcolor.getGreen() == 0 && leftcolor.getBlue() == 0) {
				System.out.println("왼쪽 벽에 충돌함");
				player.setLeft(false);
				player.setLeftWallCrash(true);
				
			}else if(rightcolor.getRed() == 255 && rightcolor.getGreen() == 0 && rightcolor.getBlue() == 0) {
				System.out.println("오른쪽 벽에 충돌함");
				player.setRight(false);
				player.setRightWallCrash(true);
			
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
				
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
