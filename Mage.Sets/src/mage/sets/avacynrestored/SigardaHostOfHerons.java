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

import mage.Constants;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.HexproofAbility;
import mage.cards.CardImpl;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.PermanentCard;
import mage.game.stack.Spell;

import java.util.UUID;

/**
 * @author noxx
 */
public class SigardaHostOfHerons extends CardImpl<SigardaHostOfHerons> {

    public SigardaHostOfHerons(UUID ownerId) {
        super(ownerId, 210, "Sigarda, Host of Herons", Rarity.MYTHIC, new CardType[]{CardType.CREATURE}, "{2}{G}{W}{W}");
        this.expansionSetCode = "AVR";
        this.supertype.add("Legendary");
        this.subtype.add("Angel");

        this.color.setGreen(true);
        this.color.setWhite(true);
        this.power = new MageInt(5);
        this.toughness = new MageInt(5);

        this.addAbility(FlyingAbility.getInstance());
        this.addAbility(HexproofAbility.getInstance());

        // Spells and abilities your opponents control can't cause you to sacrifice permanents.
        this.addAbility(new SimpleStaticAbility(Constants.Zone.BATTLEFIELD, new SigardaHostOfHeronsEffect()));
    }

    public SigardaHostOfHerons(final SigardaHostOfHerons card) {
        super(card);
    }

    @Override
    public SigardaHostOfHerons copy() {
        return new SigardaHostOfHerons(this);
    }
}

class SigardaHostOfHeronsEffect extends ReplacementEffectImpl<SigardaHostOfHeronsEffect> {

    public SigardaHostOfHeronsEffect() {
        super(Constants.Duration.WhileOnBattlefield, Constants.Outcome.Benefit);
        staticText = "Spells and abilities your opponents control can't cause you to sacrifice permanents";
    }

    public SigardaHostOfHeronsEffect(final SigardaHostOfHeronsEffect effect) {
        super(effect);
    }

    @Override
    public SigardaHostOfHeronsEffect copy() {
        return new SigardaHostOfHeronsEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        return true;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        if (event.getType() == GameEvent.EventType.SACRIFICE_PERMANENT) {
            MageObject object = game.getObject(event.getSourceId());
            if (object instanceof PermanentCard) {
                if (game.getOpponents(source.getControllerId()).contains(((PermanentCard)object).getControllerId())) {
                    return true;
                }
            }
            if (object instanceof Spell) {
                if (game.getOpponents(source.getControllerId()).contains(((Spell)object).getControllerId())) {
                    return true;
                }
            }
        }
        return false;
    }

}