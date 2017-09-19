package servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = -1207677804111845832L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		BufferedImage image = new BufferedImage(60,20,BufferedImage.TYPE_INT_RGB);
		Random ran = new Random();
		int lineCount = 5;
		Graphics g = image.getGraphics();
		g.setColor(new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256)));
		g.fillRect(0,0,60,20);
		g.setColor(new Color(0,0,0));
		String number = Integer.toString(ran.nextInt(899999)+100000);
		HttpSession session = request.getSession();
		session.setAttribute("number",number);
		g.drawString(number,5,15);
		for(int i=0;i<lineCount;i++){
			g.drawLine(ran.nextInt(60),ran.nextInt(20),ran.nextInt(60),ran.nextInt(20));
		}
		OutputStream os = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		encoder.encode(image);
	}

}
