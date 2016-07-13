package gen.display.report;

public class TemporaryClass {

    long ledger_id = 0;
    String ledger_name = "";
    long cashgrp_ids = 0;
    String cashgrp_name = "";
    int group_under = 0;

    public int getGroup_under() {
        return group_under;
    }

    public void setGroup_under(int group_under) {
        this.group_under = group_under;
    }

    public long getStock_item_id() {
        return stock_item_id;
    }

    public String getStock_item_name() {
        return stock_item_name;
    }
    long stock_item_id = 0;
    String stock_item_name = "";

    public long getLedger_id() {
        return ledger_id;
    }

    public void setLedger_id(long ledger_id) {
        this.ledger_id = ledger_id;
    }

    public void setStock_item_id(long stock_item_id) {
        this.stock_item_id = stock_item_id;
    }

    public void setStock_item_name(String stock_item_name) {
        this.stock_item_name = stock_item_name;
    }

    public void setLedger_name(String ledger_name) {
        this.ledger_name = ledger_name;
    }

    public String getLedger_name() {
        return ledger_name;
    }

    public long getCashgrp_ids() {
        return cashgrp_ids;
    }

    public void setCashgrp_ids(long cashgrp_ids) {
        this.cashgrp_ids = cashgrp_ids;
    }

    public String getCashgrp_name() {
        return cashgrp_name;
    }

    public void setCashgrp_name(String cashgrp_name) {
        this.cashgrp_name = cashgrp_name;
    }
}
