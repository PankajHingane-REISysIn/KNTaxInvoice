package gen.feature.production;

public class TemporaryClass {

    String finish_item_name = "";
    int finish_item_id = 0;
    String raw_item_name = "";
    int raw_item_id = 0;

    public int getFinish_item_id() {
        return finish_item_id;
    }

    public void setFinish_item_id(int finish_item_id) {
        this.finish_item_id = finish_item_id;
    }

    public void setFinish_item_name(String finish_item_name) {
        this.finish_item_name = finish_item_name;
    }

    public void setRaw_item_id(int raw_item_id) {
        this.raw_item_id = raw_item_id;
    }

    public void setRaw_item_name(String raw_item_name) {
        this.raw_item_name = raw_item_name;
    }

    public String getFinish_item_name() {
        return finish_item_name;
    }

    public int getRaw_item_id() {
        return raw_item_id;
    }

    public String getRaw_item_name() {
        return raw_item_name;
    }
}