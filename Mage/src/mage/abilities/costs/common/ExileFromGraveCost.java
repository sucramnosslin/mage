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

package mage.abilities.costs.common;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.costs.CostImpl;
import mage.cards.Card;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.game.Game;
import mage.players.Player;
import mage.target.common.TargetCardInASingleGraveyard;
import mage.target.common.TargetCardInYourGraveyard;
import mage.util.CardUtil;

/**
 *
 * @author nantuko
 */
public class ExileFromGraveCost extends CostImpl<ExileFromGraveCost> {

    public ExileFromGraveCost(TargetCardInYourGraveyard target) {
        this.addTarget(target);
        if (target.getMaxNumberOfTargets() > 1) {
            this.text = "Exile " + CardUtil.numberToText(target.getMaxNumberOfTargets()) + " " + target.getTargetName();
        }
        else {
            this.text = "Exile " + target.getTargetName();
        }
        if (!this.text.endsWith(" from your graveyard")) {
            this.text = this.text + " from your graveyard";
        }        
    }

    public ExileFromGraveCost(TargetCardInYourGraveyard target, String text) {
        this(target);
        this.text = text;
    }

    public ExileFromGraveCost(TargetCardInASingleGraveyard target) {
        this.addTarget(target);
        this.text = "Exile " + target.getTargetName();
    }


    public ExileFromGraveCost(ExileFromGraveCost cost) {
        super(cost);
    }

    @Override
    public boolean pay(Ability ability, Game game, UUID sourceId, UUID controllerId, boolean noMana) {
        if (targets.choose(Outcome.Exile, controllerId, sourceId, game)) {
            for (UUID targetId: targets.get(0).getTargets()) {
                Card card = game.getCard(targetId);
                if (card == null || !game.getState().getZone(targetId).equals(Zone.GRAVEYARD)) {
                    return false;
                }
                paid |= card.moveToZone(Zone.EXILED, sourceId, game, false);
            }
        }
        return paid;
    }

    @Override
    public boolean canPay(UUID sourceId, UUID controllerId, Game game) {
        return targets.canChoose(controllerId, game);
    }

    @Override
    public ExileFromGraveCost copy() {
        return new ExileFromGraveCost(this);
    }

}
