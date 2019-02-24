/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.nadreceiver.internal;

import static org.openhab.binding.nadreceiver.internal.NadReceiverBindingConstants.NAD_RECEIVER_THING_TYPE;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The {@link NadReceiverHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Marc Chételat - Initial contribution
 */
@Component(configurationPid = "binding.nadreceiver", service = ThingHandlerFactory.class)
public class NadReceiverHandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(NAD_RECEIVER_THING_TYPE);

    private NadSourcesOptionProvider stateOptionProvider;

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (NAD_RECEIVER_THING_TYPE.equals(thingTypeUID)) {
            return new NadReceiverHandler(thing, stateOptionProvider);
        }

        return null;
    }

    @Reference
    protected void setPresetChannelTypeProvider(NadSourcesOptionProvider stateOptionProvider) {
        this.stateOptionProvider = stateOptionProvider;
    }

    protected void unsetPresetChannelTypeProvider(NadSourcesOptionProvider stateOptionProvider) {
        this.stateOptionProvider = null;
    }

}
