package t5.ipe.cucumber.core.web.util;
import javax.net.ssl.*;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class SSLUtils {

    public static void trustReportPortalCert() {
        try {

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            InputStream certStream =
                    SSLUtils.class.getClassLoader().getResourceAsStream("certs/reportportal.pem");

            X509Certificate cert =
                    (X509Certificate) cf.generateCertificate(certStream);

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("reportportal", cert);

            TrustManagerFactory tmf =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            tmf.init(keyStore);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
