package gen.other.login;

import exception.NetWorkValidationException;
import java.io.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class SampleWebServiceGet {

    public String callRestService(String request) throws Exception {
        String response = "";
        try {
            HttpClient client = new HttpClient();
            GetMethod method = new GetMethod(request);

            // Send GET request
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                throw new NetWorkValidationException("Resoponce is not ok.");
            }
            InputStream rstream = null;

            // Get the response body
            rstream = method.getResponseBodyAsStream();

            // Process the response 
            BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Print It---->>>" + line);
                response = line;
            }
            br.close();
        } catch (Exception ex) {
            throw ex;
        }
        return response;

    }
}