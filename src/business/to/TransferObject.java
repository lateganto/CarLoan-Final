package business.to;

import java.util.List;

/**
 * JavaDoc
 */
public interface TransferObject {

    List<Object> getAttributes();

    String getId();

    void setId(String id);

    TransferObject getData();

}
