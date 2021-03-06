/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.championsofkamigawa;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Rarity;
import mage.MageInt;
import mage.abilities.condition.common.ControlsPermanentCondition;
import mage.abilities.common.CantBlockAbility;
import mage.abilities.costs.mana.ColoredManaCost;
import mage.abilities.decorator.ConditionalGainActivatedAbility;
import mage.abilities.effects.common.RegenerateSourceEffect;
import mage.cards.CardImpl;
import mage.constants.ColoredManaSymbol;
import mage.constants.Zone;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.predicate.mageobject.SubtypePredicate;

/**
 *
 * @author LevelX
 */
public class VillainousOgre extends CardImpl<VillainousOgre> {

    private static final String rule = "As long as you control a Demon, {this} has {B}: Regenerate Villainous Ogre";
    private static final FilterControlledPermanent filter = new FilterControlledPermanent("Demon");
    static {
        filter.add(new SubtypePredicate("Demon"));
    }

    public VillainousOgre(UUID ownerId) {
        super(ownerId, 148, "Villainous Ogre", Rarity.COMMON, new CardType[]{CardType.CREATURE}, "{2}{B}");
        this.expansionSetCode = "CHK";
        this.subtype.add("Ogre");
        this.subtype.add("Warrior");

        this.color.setBlack(true);
        this.power = new MageInt(3);
        this.toughness = new MageInt(2);

        this.addAbility(new CantBlockAbility());

        // As long as you control a Demon, Villainous Ogre has "{B}: Regenerate Villainous Ogre.
        this.addAbility( new ConditionalGainActivatedAbility(
                Zone.BATTLEFIELD,
                new RegenerateSourceEffect(), 
                new ColoredManaCost(ColoredManaSymbol.B),
                new ControlsPermanentCondition(filter), 
                rule));        
    }

    public VillainousOgre(final VillainousOgre card) {
        super(card);
    }

    @Override
    public VillainousOgre copy() {
        return new VillainousOgre(this);
    }    

}
