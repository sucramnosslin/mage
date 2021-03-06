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
package mage.sets.bornofthegods;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.DoIfCostPaid;
import mage.abilities.keyword.InspiredAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.sets.bornofthegods.TokenAndCounters.CentaurEnchantmentCreatureToken;

/**
 *
 * @author LevelX2
 */
public class PheresBandRaiders extends CardImpl<PheresBandRaiders> {

    public PheresBandRaiders(UUID ownerId) {
        super(ownerId, 133, "Pheres-Band Raiders", Rarity.UNCOMMON, new CardType[]{CardType.CREATURE}, "{5}{G}");
        this.expansionSetCode = "BNG";
        this.subtype.add("Centaur");
        this.subtype.add("Warrior");

        this.color.setGreen(true);
        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        // <i>Inspired</i> - Whenever Pheres-Band Raiders becomes untapped, you may pay {2}{G}. If you do, put a 3/3 green Centaur enchantment creature token onto the battlefield.
        this.addAbility(new InspiredAbility(new DoIfCostPaid(new CreateTokenEffect(new CentaurEnchantmentCreatureToken()), new ManaCostsImpl("{2}{G}"))));

    }

    public PheresBandRaiders(final PheresBandRaiders card) {
        super(card);
    }

    @Override
    public PheresBandRaiders copy() {
        return new PheresBandRaiders(this);
    }
}


