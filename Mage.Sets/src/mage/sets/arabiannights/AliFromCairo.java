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
package mage.sets.arabiannights;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.cards.CardImpl;
import mage.constants.*;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.players.Player;

/**
 *
 * @author KholdFuzion
 */
public class AliFromCairo extends CardImpl<AliFromCairo> {

    public AliFromCairo(UUID ownerId) {
        super(ownerId, 44, "Ali from Cairo", Rarity.RARE, new CardType[]{CardType.CREATURE}, "{2}{R}{R}");
        this.expansionSetCode = "ARN";
        this.subtype.add("Human");

        this.color.setRed(true);
        this.power = new MageInt(0);
        this.toughness = new MageInt(1);

        // Damage that would reduce your life total to less than 1 reduces it to 1 instead.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new AliFromCairoReplacementEffect()));
    }

    public AliFromCairo(final AliFromCairo card) {
        super(card);
    }

    @Override
    public AliFromCairo copy() {
        return new AliFromCairo(this);
    }
}

class AliFromCairoReplacementEffect extends ReplacementEffectImpl<AliFromCairoReplacementEffect> {

    public AliFromCairoReplacementEffect() {
        super(Duration.WhileOnBattlefield, Outcome.Benefit);
        staticText = "Damage that would reduce your life total to less than 1 reduces it to 1 instead";
    }

    public AliFromCairoReplacementEffect(final AliFromCairoReplacementEffect effect) {
        super(effect);
    }

    @Override
    public AliFromCairoReplacementEffect copy() {
        return new AliFromCairoReplacementEffect(this);
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        if (event.getType().equals(GameEvent.EventType.DAMAGE_CAUSES_LIFE_LOSS)) {
            Permanent permanent = game.getPermanent(source.getSourceId());
            if (permanent != null) {
                Player controller = game.getPlayer(source.getControllerId());
                if (controller != null
                        && (controller.getLife() > 0) &&(controller.getLife() - event.getAmount()) < 1
                        && event.getPlayerId().equals(controller.getId())
                        ) {
                    event.setAmount(controller.getLife() - 1);
                    //unsure how to make this comply with
                    // 10/1/2008: The ability doesn't change how much damage is dealt;
                    // it just changes how much life that damage makes you lose.
                    // An effect such as Spirit Link will see the full amount of damage being dealt.
                }
            }
        }

        return false;
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return false;
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        return false;
    }

}
