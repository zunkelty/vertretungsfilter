package de.zunk.vertretungsalarm.server;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class TestingClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String args[]) {

		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage(
					"http://light.dsbcontrol.de/DSBlightW***REMOVED***ite/(S(yfwrenszkxbcbu2142ouknij))/Homepage/Player.aspx?ID=caba86fb-3351-463a-ac8f-165a210dc702&MaxWidth=1380&MaxHeight=760&MyDate=0");

			// System.out.println(page.getDocumentElement().asXml());

			final List<FrameWindow> window = page.getFrames();
			final HtmlPage pageTwo = (HtmlPage) window.get(0).getEnclosedPage();

			// System.out.println(pageTwo.getDocumentElement().asXml());

			webClient.waitForBackgroundJavaScriptStartingBefore(5000);

			HtmlForm form1 = pageTwo.getFormByName("form1");

			HtmlSubmitInput submit = form1.getInputByName("ctl02$btnLogin");
			final HtmlTextInput textFieldUser = form1.getInputByName("ctl02$txtBenutzername");
			final HtmlPasswordInput textFieldPw = form1.getInputByName("ctl02$txtPasswort");

			textFieldUser.type("***REMOVED***");
			textFieldPw.type("***REMOVED***-han");

			HtmlPage resultPage = submit.click();

			System.out.println(resultPage.asText());

			final List<FrameWindow> window2 = resultPage.getFrames();
			final HtmlPage pageTwo2 = (HtmlPage) window2.get(1).getEnclosedPage();

		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
