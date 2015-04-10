package engine.shop;

import engine.fieldsetting.Settable;
import engine.gameobject.Graphic;

@Settable
public class UpgradeTagSimple implements UpgradeTag {
    private String name;
    private Graphic shopGraphic;
    private String description;
    private Purchasable purchasable;

    public UpgradeTagSimple () {
        name = "";
        description = "";
        shopGraphic = new Graphic();
        purchasable = null;
    }

    @Override
    public String getName () {
        return name;
    }

    @Override
    public Graphic getShopGraphic () {
        return shopGraphic;
    }

    @Override
    public String getDescription () {
        return description;
    }

    @Override
    public double getValue () {
        return purchasable.getValue();
    }

    @Settable
    public void setName (String name) {
        this.name = name;
    }

    @Settable
    public void setShopGraphic (Graphic shopGraphic) {
        this.shopGraphic = shopGraphic;
    }

    @Settable
    public void setDescription (String description) {
        this.description = description;
    }

    @Settable
    public void setPurchasable (Purchasable purchasable) {
        this.purchasable = purchasable;
    }

}
