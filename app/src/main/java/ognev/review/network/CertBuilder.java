package ognev.review.network;

import android.app.Application;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import ognev.review.BuildConfig;
import ognev.review.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 */
public class CertBuilder {
  public static OkHttpClient createClient(Application application) {

    OkHttpClient client = null;
    CertificateFactory cf = null;
    InputStream cert = null;
    Certificate ca = null;
    SSLContext sslContext = null;

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
        : HttpLoggingInterceptor.Level.NONE);

    try {
      cf = CertificateFactory.getInstance("X.509");
      cert = application
          .getResources()
          .openRawResource(R.raw.cert_key); // Place your 'my_cert.crt' file in `res/raw`

      ca = cf.generateCertificate(cert);
      cert.close();

      String keyStoreType = KeyStore.getDefaultType();
      KeyStore keyStore = KeyStore.getInstance(keyStoreType);
      keyStore.load(null, null);
      keyStore.setCertificateEntry("ca", ca);

      String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
      TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
      tmf.init(keyStore);


      final TrustManager[] trustAllCerts = new TrustManager[] {
          new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws
                CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return new java.security.cert.X509Certificate[]{};
            }
          }
      };


      sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, null);

      client = new OkHttpClient.Builder()
          .sslSocketFactory(sslContext.getSocketFactory())
          .hostnameVerifier(getHostnameVerifier())
          .addInterceptor(logging)
          .readTimeout(60, TimeUnit.SECONDS)
          .writeTimeout(60, TimeUnit.SECONDS)
          .retryOnConnectionFailure(false)
          //.addInterceptor(new JwtTokenInterceptor())
          .build();
    } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException | KeyManagementException e) {
      e.printStackTrace();
    }

    return client;
  }



  private static HostnameVerifier getHostnameVerifier() {
    return new HostnameVerifier() {
      @Override
      public boolean verify(String hostname, SSLSession session) {
        return true; // verify always returns true, which could cause insecure network traffic due to trusting TLS/SSL server certificates for wrong hostnames
      }
    };
  }
}
