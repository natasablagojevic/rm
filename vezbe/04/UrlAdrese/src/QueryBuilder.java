import java.net.*;
import java.nio.charset.StandardCharsets;

public class QueryBuilder {
  // StringBuffer je Thread safe
  private final StringBuilder query;
  public QueryBuilder(String endpoint) {
    this.query = new StringBuilder(endpoint);
    this.query.append('?');
  }

  public QueryBuilder append(String key, String value) {
    key = URLEncoder.encode(key, StandardCharsets.UTF_8);
    value = URLEncoder.encode(value, StandardCharsets.UTF_8);
    this.query.append(key).append('=').append(value).append('&');
    return this;
  }

  @Override
  public String toString() {
    return this.query.toString();
  }

  public URL toUrl() throws MalformedURLException {
    return new URL(this.toString());
  }
}
