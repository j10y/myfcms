/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-10-22</p>
 * <p>���£�</p>
 */
package base.web.tag;

import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.context.WebApplicationContext;

import base.util.ApplicationParameter;
import base.util.StringUtil;
import base.util.WebAppUtil;


/**
 * <p>
 * ����: ToPage
 * </p>
 * <p>
 * ����: ��ҳ����ʾ��ǩ��
 * </p>
 */
public class ToPage extends TagSupport {

    private String pageNo;

    private String recPerPage;

    private String totalRec;

    private String queryFormId;

    private String queryFormPageNoId;

    private String queryFormRecPerPageId;
    
    private String isPhoto;

    private static WebApplicationContext webApplicationContext = null;

    public int doStartTag() throws JspException {
        try {
            //����WebApplicationContext����
            if (webApplicationContext == null) {
                webApplicationContext = WebAppUtil
                        .getWebApplicationContext(pageContext
                                .getServletContext());
            }
            ServletRequest request = pageContext.getRequest();
            Locale locale = request.getLocale();
            //��ȡ�ַ�����Դ
            String totalLabel = webApplicationContext.getMessage(
                    "public.label.total", null, "no message", locale);
            String pageLabel = webApplicationContext.getMessage(
                    "public.label.page", null, "no message", locale);
            String recordLabel = webApplicationContext.getMessage(
                    "public.label.record", null, "no message", locale);
            String firstPageLabel = webApplicationContext.getMessage(
                    "public.label.firstPage", null, "no message", locale);
            String previousPageLabel = webApplicationContext.getMessage(
                    "public.label.previousPage", null, "no message", locale);
            String lastPageLabel = webApplicationContext.getMessage(
                    "public.label.lastPage", null, "no message", locale);
            String nextPageLabel = webApplicationContext.getMessage(
                    "public.label.nextPage", null, "no message", locale);
            String recPerPageLabel = webApplicationContext.getMessage(
                    "public.label.recPerPage", null, "no message", locale);
            String goToPageLabel = webApplicationContext.getMessage(
                    "public.label.goToPage", null, "no message", locale);
            //�������Ӱ�����Ŀ���ͻ�ȡ��Ӱ���ַ�����Դ
            if (isPhoto != null && isPhoto.equals("1")) {
            	totalLabel = webApplicationContext.getMessage(
                        "public.label.totalPhoto", null, "no message", locale);
                pageLabel = webApplicationContext.getMessage(
                        "public.label.pagePhoto", null, "no message", locale);
                recordLabel = webApplicationContext.getMessage(
                        "public.label.recordPhoto", null, "no message", locale);
                firstPageLabel = webApplicationContext.getMessage(
                        "public.label.firstPagePhoto", null, "no message", locale);
                previousPageLabel = webApplicationContext.getMessage(
                        "public.label.previousPagePhoto", null, "no message", locale);
                lastPageLabel = webApplicationContext.getMessage(
                        "public.label.lastPagePhoto", null, "no message", locale);
                nextPageLabel = webApplicationContext.getMessage(
                        "public.label.nextPagePhoto", null, "no message", locale);
                recPerPageLabel = webApplicationContext.getMessage(
                        "public.label.recPerPagePhoto", null, "no message", locale);
                goToPageLabel = webApplicationContext.getMessage(
                        "public.label.goToPagePhoto", null, "no message", locale);
            }
            
            
            //��ȡӦ�ó������
            ApplicationParameter parameters = (ApplicationParameter) webApplicationContext
                    .getBean("applicationParameter");
            Long recPerPageParam = null;
            if (parameters != null) {
                recPerPageParam = StringUtil.stringToLong((String) parameters
                        .findParameterByName("recPerPage"));
            }
            if (recPerPageParam == null)
                recPerPageParam = new Long(10);
            //��ȡ���õ�����
            Long pageNoL = StringUtil
                    .stringToLong((String) ExpressionEvaluatorManager.evaluate(
                            "pageNo", pageNo, String.class, this, pageContext));
            Long totalRecL = StringUtil
                    .stringToLong((String) ExpressionEvaluatorManager.evaluate(
                            "totalRec", totalRec, String.class, this,
                            pageContext));
            Long recPerPageL = StringUtil
                    .stringToLong((String) ExpressionEvaluatorManager.evaluate(
                            "recPerPage", recPerPage, String.class, this,
                            pageContext));
            //�ж����õ������Ƿ�Ϸ����粻�Ϸ��������Ĭ������ֵ
            if (pageNoL == null)
                pageNoL = new Long(1);
            if (totalRecL == null)
                totalRecL = new Long(0);
            if (recPerPageL == null || recPerPageL.longValue() <= 0)
                recPerPageL = recPerPageParam;
            //������ҳ��
            long totalPage = (totalRecL.longValue() % recPerPageL.longValue() == 0) ? (totalRecL
                    .longValue() / recPerPageL.longValue())
                    : ((totalRecL.longValue() / recPerPageL.longValue()) + 1);
            if (pageNoL.longValue() > totalPage)
                pageNoL = new Long(totalPage);
            if (pageNoL.longValue() < 1)
                pageNoL = new Long(1);
            //���ҳ������
            JspWriter out = pageContext.getOut();
            out.println("<script language=\"javascript\">");
            out.println("function queryFormSubmit(pageNoParam) {");
            out.println("  document.getElementById(\"" + queryFormPageNoId
                    + "\").value = pageNoParam;");
            out
                    .println("  document.getElementById(\""
                            + queryFormRecPerPageId
                            + "\").value = document.getElementById(\"recPerPageForDisplay\").value;");
            out.println("if (window.urlSubmit != null && window.urlSubmit() == false) ");
            out.println("  return;");
            out.println("  document.getElementById(\"" + queryFormId
                    + "\").submit();");
            out.println("}");
            //      out.println("function displayAllRecordsInOnePage() {");
            //      out.println(" document.getElementById(\"" + queryFormPageNoId +
            // "\").value = 1;");
            //      out.println(" document.getElementById(\"" + queryFormRecPerPageId
            // + "\").value = " + 10000);
            //      out.println(" document.getElementById(\"" + queryFormId +
            // "\").submit();");
            //      out.println("}");
            out.println("</script>");
            out
                    .println("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
            out.println("  <tr>");
            out.println("    <td align=\"center\">");
            out.println(totalLabel + totalRecL + recordLabel + "&nbsp;");
            out.println(totalLabel + totalPage + pageLabel + "&nbsp;");
            if (pageNoL.longValue() > 1) {
                out.println("<a href=\"javascript:queryFormSubmit(1);\">"
                        + firstPageLabel + "</a>" + "&nbsp;");
                out.println("<a href=\"javascript:queryFormSubmit("
                        + (pageNoL.longValue() - 1) + ");\">"
                        + previousPageLabel + "</a>" + "&nbsp;");
            } else {
                out.println(firstPageLabel + "&nbsp;");
                out.println(previousPageLabel + "&nbsp;");
            }
            if (pageNoL.longValue() < totalPage) {
                out.println("<a href=\"javascript:queryFormSubmit("
                        + (pageNoL.longValue() + 1) + ");\">" + nextPageLabel
                        + "</a>" + "&nbsp;");
                out.println("<a href=\"javascript:queryFormSubmit(" + totalPage
                        + ");\">" + lastPageLabel + "</a>" + "&nbsp;");
            } else {
                out.println(nextPageLabel + "&nbsp;");
                out.println(lastPageLabel + "&nbsp;");
            }
            //      out.println("<a
            // href=\"javascript:displayAllRecordsInOnePage();\"><img
            // style=\"vertical-align:bottom\" src=\"images/common/full.jpg\"
            // border=\"0\" alt=\"" + displayAll + "\"></a>");
            //      out.println(" &nbsp;");
            out
                    .println(recPerPageLabel
                            + "<input type=\"text\" id=\"recPerPageForDisplay\" size=\"3\" value=\""
                            + recPerPageL + "\">" + "&nbsp;");
            out.println(goToPageLabel + "<select id=\"goToPageEdit\">");
            for (int i = 1; i <= totalPage; i++) {
                out.println("<option value=\"" + i + "\""
                        + ((pageNoL.intValue() == i) ? "selected" : "")
                        + ">" + i + "</option>");
            }
            out.println("</select>");
            out
                    .println("<input class=\"button1\" type=\"button\" value=\"Go\" onclick=\"javascript:queryFormSubmit(document.getElementById('goToPageEdit').value)\">");
            out.println("&nbsp;</td>");
            out.println("  </tr>");
            out.println("</table>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return TagSupport.SKIP_BODY;
    }

    /**
     * ����: ���� queryFormId
     */
    public String getQueryFormId() {
        return queryFormId;
    }

    /**
     * ����: ���� queryFormId
     */
    public void setQueryFormId(String queryFormId) {
        this.queryFormId = queryFormId;
    }

    /**
     * ����: ���� pageNo
     */
    public String getPageNo() {
        return pageNo;
    }

    /**
     * ����: ���� pageNo
     */
    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * ����: ���� recPerPage
     */
    public String getRecPerPage() {
        return recPerPage;
    }

    /**
     * ����: ���� recPerPage
     */
    public void setRecPerPage(String recPerPage) {
        this.recPerPage = recPerPage;
    }

    /**
     * ����: ���� totalRec
     */
    public String getTotalRec() {
        return totalRec;
    }

    /**
     * ����: ���� totalRec
     */
    public void setTotalRec(String totalRec) {
        this.totalRec = totalRec;
    }

    /**
     * ����: ���� queryFormPageNoId
     */
    public String getQueryFormPageNoId() {
        return queryFormPageNoId;
    }

    /**
     * ����: ���� queryFormPageNoId
     */
    public void setQueryFormPageNoId(String queryFormPageNoId) {
        this.queryFormPageNoId = queryFormPageNoId;
    }

    /**
     * ����: ���� queryFormRecPerPageId
     */
    public String getQueryFormRecPerPageId() {
        return queryFormRecPerPageId;
    }

    /**
     * ����: ���� queryFormRecPerPageId
     */
    public void setQueryFormRecPerPageId(String queryFormRecPerPageId) {
        this.queryFormRecPerPageId = queryFormRecPerPageId;
    }

    /**
     * ����: ���� isPhoto
     */
	public String getIsPhoto() {
		return isPhoto;
	}

    /**
     * ����: ���� isPhoto
     */
	public void setIsPhoto(String isPhoto) {
		this.isPhoto = isPhoto;
	}
    
    
}

