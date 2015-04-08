package engine.gameobject.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import engine.gameobject.weapon.upgradable.Upgradable;


public class UpgradeBundleSimple implements UpgradeBundle {

    private List<Upgrade> upgrades;

    public UpgradeBundleSimple (Upgrade ... upgrades) {
        this.upgrades = new ArrayList<>(Arrays.asList(upgrades));
    }

    @Override
    public void applyUpgrades (Map<Class<? extends Upgradable>, Upgradable> upgradables) {
        upgrades.forEach(upgrade -> {
            Class<? extends Upgradable> upgradeType = upgrade.getType(); 
            
            //TODO: add Optional to clean this up?
            if (upgradables.keySet().contains(upgradeType)){
                // upgrade existing upgradable
                upgrade.setDecorated(upgradables.get(upgradeType));
            }
            else {
                // create new upgradable
                upgrade.setDefault();
            }
            // put new upgradable in the map
            upgradables.put(upgradeType, upgradeType.cast(upgrade));
            // TODO: must add listener to Behavior list to update
        });
    }

    @Override
    public boolean isFinalUpgrade () {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getValue () {
        return 0;
    }

    @Override
    public String getGraphic () {
        // TODO Auto-generated method stub
        return null;
    }

 

}
