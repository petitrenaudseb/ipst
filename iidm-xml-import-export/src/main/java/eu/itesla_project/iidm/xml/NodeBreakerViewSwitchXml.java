/**
 * Copyright (c) 2016, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package eu.itesla_project.iidm.xml;

import eu.itesla_project.iidm.network.Switch;
import eu.itesla_project.iidm.network.SwitchKind;
import eu.itesla_project.iidm.network.VoltageLevel;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.List;

/**
 * @author Geoffroy Jamgotchian <geoffroy.jamgotchian at rte-france.com>
 */
public class NodeBreakerViewSwitchXml extends SwitchXml<VoltageLevel.NodeBreakerView.SwitchAdder> {

    static final NodeBreakerViewSwitchXml INSTANCE = new NodeBreakerViewSwitchXml();

    @Override
    protected void writeRootElementAttributes(Switch s, VoltageLevel vl, XmlWriterContext context) throws XMLStreamException {
        super.writeRootElementAttributes(s, vl, context);
        VoltageLevel.NodeBreakerView v = vl.getNodeBreakerView();
        context.getWriter().writeAttribute("node1", Integer.toString(v.getNode1(s.getId())));
        context.getWriter().writeAttribute("node2", Integer.toString(v.getNode2(s.getId())));
    }

    @Override
    protected VoltageLevel.NodeBreakerView.SwitchAdder createAdder(VoltageLevel vl) {
        return vl.getNodeBreakerView().newSwitch();
    }

    @Override
    protected Switch readRootElementAttributes(VoltageLevel.NodeBreakerView.SwitchAdder adder, XMLStreamReader reader, List<Runnable> endTasks) {
        boolean open = readBoolAttribute(reader, "open");
        SwitchKind kind = SwitchKind.valueOf(reader.getAttributeValue(null, "kind"));
        boolean retained = readBoolAttribute(reader, "retained");
        int node1 = readIntAttribute(reader, "node1");
        int node2 = readIntAttribute(reader, "node2");
        return adder.setKind(kind)
                .setRetained(retained)
                .setOpen(open)
                .setNode1(node1)
                .setNode2(node2)
                .add();
    }
}
