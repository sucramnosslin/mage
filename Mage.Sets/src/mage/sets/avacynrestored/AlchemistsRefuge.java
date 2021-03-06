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
package mage.sets.avacynrestored;

import mage.constants.*;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.CompositeCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.effects.common.AddContinuousEffectToGame;
import mage.abilities.mana.ColorlessManaAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.game.Game;

import java.util.UUID;
import mage.ObjectColor;
import mage.abilities.effects.common.continious.CastAsThoughItHadFlashEffect;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.CardTypePredicate;
import mage.filter.predicate.mageobject.ColorPredicate;

/**
 *
 * @author noxx

 */
public class AlchemistsRefuge extends CardImpl<AlchemistsRefuge> {

    private static final FilterCreatureCard filter = new FilterCreatureCard("nonland cards");
    static {
        filter.add(Predicates.not(new CardTypePredicate(CardType.LAND)));
    }

    public AlchemistsRefuge(UUID ownerId) {
        super(ownerId, 225, "Alchemist's Refuge", Rarity.RARE, new CardType[]{CardType.LAND}, "");
        this.expansionSetCode = "AVR";

        // {tap}: Add {1} to your mana pool.
        this.addAbility(new ColorlessManaAbility());

        // {G}{U}, {tap}: You may cast nonland cards this turn as though they had flash.
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, 
                new AddContinuousEffectToGame(new CastAsThoughItHadFlashEffect(Duration.EndOfTurn, filter)),
                new CompositeCost(new ManaCostsImpl("{G}{U}"), new TapSourceCost(), "{G}{U}, {T}")));
    }

    public AlchemistsRefuge(final AlchemistsRefuge card) {
        super(card);
    }

    @Override
    public AlchemistsRefuge copy() {
        return new AlchemistsRefuge(this);
    }
}
