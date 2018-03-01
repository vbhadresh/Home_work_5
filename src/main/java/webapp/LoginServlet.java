package webapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;



@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	
	  private static ValidatorFactory vf;
	  private static Validator validator;
	  private static CustomValidator validateFields;
	  private String zipCode;
	  private String emailAddress;
	  private String city;
	  private String streetAddress;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/GetFormData.jsp").forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 zipCode = request.getParameter("zipcode");
		 emailAddress = request.getParameter("emailAddress");
		 city = request.getParameter("city");
		 streetAddress = request.getParameter("streetAddress");
		String errorMsg="";
	    vf = Validation.buildDefaultValidatorFactory();
	    validator = vf.getValidator();	    
	    validateFields = new CustomValidator(zipCode,emailAddress);
	    Set<ConstraintViolation<CustomValidator>> violations = validator.validate(validateFields);

	    for ( ConstraintViolation<CustomValidator> viol : violations){
	      errorMsg=viol.getMessage().toString();
	    }
	    if(errorMsg.equals("")||errorMsg.equals("null")){
	    	try{
	    		System.out.println(request.getParameter("zipcode"));
	    		System.out.println(request.getParameter("customerName"));
	    		System.out.println(request.getParameter("socialsecuritynumber"));
	    		System.out.println(request.getParameter("zipcode"));
	    		System.out.println(request.getParameter("emailAddress"));
	    		System.out.println(request.getParameter("streetAddress"));
	    		System.out.println(request.getParameter("city"));
	    		System.out.println(request.getParameter("state"));
	    		String latLongs[] = getLatLong(request.getParameter("zipcode"));
				HttpSession session=request.getSession();
				System.out.println(session);
				session.setAttribute("FullName", request.getParameter("customerName"));
				session.setAttribute("SSN", request.getParameter("socialsecuritynumber"));
				session.setAttribute("zipCode", request.getParameter("zipcode"));
				session.setAttribute("email", request.getParameter("emailAddress"));
				session.setAttribute("address", request.getParameter("streetAddress"));
				session.setAttribute("city", request.getParameter("city"));
				session.setAttribute("state", request.getParameter("state"));
				session.setAttribute("lat", latLongs[0]);
				session.setAttribute("long", latLongs[1]);
				System.out.println("hgj"+request.getParameter("city"));
				request.getRequestDispatcher("/WEB-INF/views/ValidateFormDate.jsp").forward(request, response);
//				System.out.println("Servlet, JSP , Session Bean and  Google API validated Successfully ");
//				System.exit(0);
	    	}catch(Exception e){
	    		System.err.println("Failed in Session recieving");
	    	}
	    	
	    }
	    else{
	    	request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
	    	System.err.println(errorMsg);
	    	System.exit(0);
	    }
	 
		
	}
	
	public String[] getLatLong(String address) throws Exception {
		int responseCode = 0;
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	    URL url = new URL(api);
	    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    httpConnection.connect();
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	      Document document = builder.parse(httpConnection.getInputStream());
	      XPathFactory xPathfactory = XPathFactory.newInstance();
	      XPath xpath = xPathfactory.newXPath();
	      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	      String status = (String)expr.evaluate(document, XPathConstants.STRING);
	      if(status.equals("OK"))
	      {
	         expr = xpath.compile("//geometry/location/lat");
	         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         expr = xpath.compile("//geometry/location/lng");
	         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         System.out.println(longitude);
	         return new String[] {latitude, longitude};
	      }
	      else
	      {
	         throw new Exception("Error from the API - response status: "+status);
	      }
	    }
		return null;
	}

}