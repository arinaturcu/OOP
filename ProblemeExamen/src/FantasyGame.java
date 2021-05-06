class WizardFactory {
    enum WizardType {
        ICE_WIZARD, FIRE_WIZARD
    }

    public static Wizard createWizard(WizardType type, String name, int shield) {
        return switch (type) {
            case ICE_WIZARD -> new IceWizard(name, shield);
            case FIRE_WIZARD -> new FireWizard(name, shield);
        };
    }
}

abstract class Wizard {
    private final String name;
    private int shield;

    public Wizard(String name, int shield) {
        this.name = name;
        this.shield = shield;
    }

    abstract public void attack(Wizard wizard);

    public String getName() {
        return name;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = Math.max(shield, 0);
    }
}

class FireWizard extends Wizard {
    public FireWizard(String name, int shield) {
        super(name, shield);
    }

    @Override
    public void attack(Wizard wizard) {
        if (this.getShield() == 0) return;
        wizard.setShield(wizard.getShield() / 4);
    }
}

class IceWizard extends Wizard {
    public IceWizard(String name, int shield) {
        super(name, shield);
    }

    @Override
    public void attack(Wizard wizard) {
        if (this.getShield() == 0) return;
        wizard.setShield(wizard.getShield() - 10);
    }
}

public class FantasyGame {
    public static void main(String[] args) {
        Wizard player1 = WizardFactory.createWizard(WizardFactory.WizardType.ICE_WIZARD, "Ice", 200);
        Wizard player2 = WizardFactory.createWizard(WizardFactory.WizardType.FIRE_WIZARD, "Fire", 100);

        while (!(player1.getShield() == 0 || player2.getShield() == 0)) {
            player1.attack(player2);
            player2.attack(player1);
            System.out.println(player1.getName() + ": " + player1.getShield() + "\n" +
                                player2.getName() + ": " + player2.getShield() + "\n");
        }

        String winnerName;
        if (player1.getShield() > 0) {
            winnerName = player1.getName();
        } else {
            winnerName = player2.getName();
        }

        System.out.println("Winner: " + winnerName);
    }
}
