package engine.gameobject.weapon.upgradetree.upgradebundle;

import engine.gameobject.Graphic;
import engine.gameobject.units.directdamage.DamageBuff;
import engine.gameobject.weapon.Upgrade;
import engine.gameobject.weapon.firingrate.FiringRateUpgrade;
import engine.gameobject.weapon.range.RangeUpgrade;
import engine.shop.ShopTagSimple;


public class TestBundle extends UpgradeBundleSimple {

    public TestBundle (int i) {
        ShopTagSimple shopTag = new ShopTagSimple();
        setShopTag(shopTag);
        if (i == 0) {
            setUpgrades(new Upgrade[] { new RangeUpgrade(50) });
            shopTag.setName("Range Upgrade");
            shopTag.setDescription("Range + 50");
            setValue(15);
        }
        if (i == 1) {
            setUpgrades(new Upgrade[] { new FiringRateUpgrade(1) });
            shopTag.setName("Firing Rate Upgrade");
            shopTag.setDescription("Firing Rate x2");
            setValue(25);
        }
        if (i == 2) {
            setUpgrades(new Upgrade[] { new DamageBuff(10) });
            shopTag.setName("Damage Upgrade");
            shopTag.setDescription("Damage + 10");
            setValue(90);
        }
        
        shopTag.setShopGraphic(new Graphic(30, 30, "Bloons_DartMonkey.png"));
        
    }
}
