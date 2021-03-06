/*
 * Copyright (c) 2015 Oracle and/or its affiliates. All rights reserved. This
 * code is released under a tri EPL/GPL/LGPL license. You can use it,
 * redistribute it and/or modify it under the terms of the:
 *
 * Eclipse Public License version 1.0
 * GNU General Public License version 2
 * GNU Lesser General Public License version 2.1
 */
package org.jruby.truffle.pack.nodes.write;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.jruby.truffle.pack.nodes.PackNode;
import org.jruby.truffle.pack.runtime.exceptions.NoImplicitConversionException;
import org.jruby.util.ByteList;
import org.jruby.util.Pack;

/**
 * Read a string that contains UU-encoded data and write as actual binary
 * data.
 */
@NodeChildren({
        @NodeChild(value = "value", type = PackNode.class),
})
public abstract class WriteUUStringNode extends PackNode {

    private final int length;
    private final boolean ignoreStar;

    public WriteUUStringNode(int length, boolean ignoreStar) {
        this.length = length;
        this.ignoreStar = ignoreStar;
    }

    @Specialization
    public Object write(long bytes) {
        throw new NoImplicitConversionException(bytes, "String");
    }

    @Specialization(guards = "isEmpty(bytes)")
    public Object writeEmpty(VirtualFrame frame, ByteList bytes) {
        return null;
    }

    @Specialization(guards = "!isEmpty(bytes)")
    public Object write(VirtualFrame frame, ByteList bytes) {
        writeBytes(frame, encode(bytes));
        return null;
    }

    @CompilerDirectives.TruffleBoundary
    private byte[] encode(ByteList bytes) {
        // TODO CS 30-Mar-15 should write our own optimizable version of UU

        final ByteList output = new ByteList();
        Pack.encodeUM(null, bytes, length, ignoreStar, 'u', output);
        return output.bytes();
    }

    protected boolean isEmpty(ByteList bytes) {
        return bytes.length() == 0;
    }

}
