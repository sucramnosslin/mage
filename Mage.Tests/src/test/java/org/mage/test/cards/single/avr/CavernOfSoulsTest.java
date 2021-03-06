package org.mage.test.cards.single.avr;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

/**
 *
 * @author noxx
 */
public class CavernOfSoulsTest extends CardTestPlayerBase {

    /**
     * Tests simple cast
     */
    @Test
    public void testCastDrake() {
        addCard(Zone.BATTLEFIELD, playerA, "Mountain", 3);
        addCard(Zone.HAND, playerA, "Cavern of Souls");
        addCard(Zone.HAND, playerA, "Azure Drake");

        setChoice(playerA, "Drake");
        setChoice(playerA, "Blue");

        playLand(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Cavern of Souls");
        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Azure Drake");

        setStopAt(1, PhaseStep.BEGIN_COMBAT);
        execute();

        assertPermanentCount(playerA, "Azure Drake", 1);
    }

    /**
     * Tests "Cavern of Souls" with "Human" creature type chosen.
     * Then tests casting Azure Drake (should fail) and Elite Vanguard (should be ok as it has "Human" subtype)
     */
    @Test
    public void testNoCastBecauseOfCreatureType() {
        addCard(Zone.BATTLEFIELD, playerA, "Mountain", 1);
        addCard(Zone.HAND, playerA, "Cavern of Souls");
        addCard(Zone.HAND, playerA, "Abuna Acolyte");
        addCard(Zone.HAND, playerA, "Elite Vanguard");

        setChoice(playerA, "Human");
        setChoice(playerA, "White");
        setChoice(playerA, "White");

        playLand(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Cavern of Souls"); // choose Human
        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Abuna Acolyte");  // not Human but Cat Cleric
        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Elite Vanguard"); // Human

        setStopAt(1, PhaseStep.BEGIN_COMBAT);
        execute();

        assertPermanentCount(playerA, "Abuna Acolyte", 0);
        assertPermanentCount(playerA, "Elite Vanguard", 1);
    }

    /**
     * Tests card can be countered for usual cast
     */
    @Test
    public void testDrakeCountered() {
        addCard(Zone.BATTLEFIELD, playerA, "Mountain", 3);
        addCard(Zone.HAND, playerA, "Island");
        addCard(Zone.HAND, playerA, "Azure Drake");

        addCard(Zone.HAND, playerB, "Remove Soul");
        addCard(Zone.BATTLEFIELD, playerB, "Island", 2);

        playLand(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Island");
        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Azure Drake");
        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerB, "Remove Soul");

        setStopAt(1, PhaseStep.BEGIN_COMBAT);
        execute();

        assertPermanentCount(playerA, "Azure Drake", 0);
        assertGraveyardCount(playerA, "Azure Drake", 1);
    }

    /**
     * Tests card can be countered for cast with Cavern of Souls
     */
    @Test
    public void testDrakeCantBeCountered() {
        addCard(Zone.BATTLEFIELD, playerA, "Mountain", 3);
        addCard(Zone.HAND, playerA, "Cavern of Souls");
        addCard(Zone.HAND, playerA, "Azure Drake");

        addCard(Zone.HAND, playerB, "Remove Soul");
        addCard(Zone.BATTLEFIELD, playerB, "Island", 2);

        setChoice(playerA, "Drake");
        setChoice(playerA, "Blue");

        playLand(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Cavern of Souls");
        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Azure Drake");
        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerB, "Remove Soul");

        setStopAt(1, PhaseStep.BEGIN_COMBAT);
        execute();

        // check wasn't countered
        assertGraveyardCount(playerA, "Azure Drake", 0);
        assertPermanentCount(playerA, "Azure Drake", 1);
    }

}
