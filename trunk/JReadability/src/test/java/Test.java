import com.basistech.readability.HtmlPage;
import com.basistech.readability.HttpPageReader;
import com.basistech.readability.PageReadException;
import com.basistech.readability.Readability;
import com.basistech.readability.TikaCharsetDetector;


public class Test {

	public static void main(String[] args) {
		try {
			HttpPageReader pageReader = new HttpPageReader();
			Readability readability = new Readability();
			readability.setPageReader(pageReader);
			readability.setReadAllPages(false);
			pageReader.setCharsetDetector(new TikaCharsetDetector());

			readability.processDocument("http://cpc.people.com.cn/GB/67481/82581/");
			System.out.println(readability.getTitle());
			System.out.println(readability.getArticleText());
			
			
		} catch (PageReadException e) {
			e.printStackTrace();
		}

	}

}
