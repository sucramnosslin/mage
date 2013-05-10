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

package mage.sets.dragonsmaze;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Duration;
import mage.Constants.Rarity;
import mage.abilities.effects.common.continious.BoostTargetEffect;
import mage.cards.SplitCard;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author LevelX2
 */


public class ProtectServe extends SplitCard<ProtectServe> {

    public ProtectServe(UUID ownerId) {
        super(ownerId, 131, "Protect", "Serve", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{2}{W}", "{1}{U}", true);
        this.expansionSetCode = "DGM";

        this.color.setWhite(true);
        this.color.setBlue(true);

        // Protect
        // Target creature gets +2/+4 until end of turn.
        getLeftHalfCard().getColor().setWhite(true);
        getLeftHalfCard().getSpellAbility().addEffect(new BoostTargetEffect(2,4, Duration.EndOfTurn));
        getLeftHalfCard().getSpellAbility().addTarget(new TargetCreaturePermanent(true));

        // Serve
        // Target creature gets -6/-0 until end of turn.
        getRightHalfCard().getColor().setBlue(true);
        getRightHalfCard().getSpellAbility().addEffect(new BoostTargetEffect(-6,0, Duration.EndOfTurn));
        getRightHalfCard().getSpellAbility().addTarget(new TargetCreaturePermanent(true));

    }

    public ProtectServe(final ProtectServe card) {
        super(card);
    }

    @Override
    public ProtectServe copy() {
        return new ProtectServe(this);
    }
}