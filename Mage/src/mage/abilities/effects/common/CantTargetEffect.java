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

package mage.abilities.effects.common;

import mage.constants.Duration;
import mage.constants.Outcome;
import mage.abilities.Ability;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.filter.FilterPermanent;
import mage.filter.FilterStackObject;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.GameEvent.EventType;
import mage.game.permanent.Permanent;
import mage.game.stack.StackObject;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class CantTargetEffect extends ReplacementEffectImpl<CantTargetEffect> {

    private FilterPermanent filterTarget;
    private FilterStackObject filterSource;

    public CantTargetEffect(FilterPermanent filterTarget, Duration duration) {
        this(filterTarget, null, duration);
    }

    public CantTargetEffect(FilterPermanent filterTarget, FilterStackObject filterSource, Duration duration) {
        super(duration, Outcome.Benefit);
        this.filterTarget = filterTarget;
        this.filterSource = filterSource;
        setText();
    }


    public CantTargetEffect(final CantTargetEffect effect) {
        super(effect);
        if (effect.filterTarget != null) {
            this.filterTarget = effect.filterTarget.copy();
        }
        if (effect.filterSource != null) {
            this.filterSource = effect.filterSource.copy();
        }
    }

    @Override
    public CantTargetEffect copy() {
        return new CantTargetEffect(this);
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
        if (event.getType() == EventType.TARGET) {
            Permanent permanent = game.getPermanent(event.getTargetId());
            if (permanent != null && filterTarget.match(permanent, source.getSourceId(), source.getControllerId(), game)) {
                if (filterSource == null) {
                    return true;
                }
                else {
                    StackObject sourceObject = game.getStack().getStackObject(event.getSourceId());
                    if (sourceObject != null && filterSource.match(sourceObject, sourceObject.getSourceId(), game)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void setText() {
        StringBuilder sb = new StringBuilder();
        sb.append(filterTarget.getMessage()).append(" can't be the targets of ");
        if (filterSource != null) {
            sb.append(filterSource.getMessage());
        }
        else {
            sb.append("spells");
        }
        if (!duration.toString().isEmpty()) {
            sb.append(" ").append(duration.toString());
        }
        staticText = sb.toString();
    }

}
