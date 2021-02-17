package pojo;

public class URL {
    private String hostname;
    private Integer port;

    public URL(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof  URL)) {
            return false;
        }
        URL url = (URL)obj;
        if (hostname.equals(((URL) obj).getHostname()) && port.intValue() == url.port.intValue()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hostname.hashCode();
    }
}
