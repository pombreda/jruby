package org.jruby.internal.runtime.builtin.definitions;

import org.jruby.Ruby;
import org.jruby.KernelModule;
import org.jruby.RubyModule;
import org.jruby.exceptions.NotImplementedError;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.builtin.definitions.ModuleDefinition;
import org.jruby.runtime.builtin.definitions.ModuleFunctionsContext;
import org.jruby.util.Asserts;

/**
 * 
 * @author jpetersen
 * @version $Revision$
 */
public class Kernel extends ModuleDefinition {
    private static final int KERNEL = 0x1000;

    private static final int AUTOLOAD = KERNEL | 0x01;
    private static final int BACKQUOTE = KERNEL | 0x02;
    private static final int BLOCK_GIVEN = KERNEL | 0x03;
    private static final int CALLER = KERNEL | 0x04;
    private static final int CATCH = KERNEL | 0x05;
    private static final int CHOMP = KERNEL | 0x06;
    private static final int CHOMP_BANG = KERNEL | 0x07;
    private static final int CHOP = KERNEL | 0x08;
    private static final int CHOP_BANG = KERNEL | 0x09;
    private static final int EVAL = KERNEL | 0x0a;
    private static final int EXIT = KERNEL | 0x0b;
    private static final int GETS = KERNEL | 0x0c;
    private static final int GLOBAL_VARIABLES = KERNEL | 0x0d;
    private static final int GSUB = KERNEL | 0x0e;
    private static final int GSUB_BANG = KERNEL | 0x0f;
    private static final int LOAD = KERNEL | 0x10;
    private static final int LOCAL_VARIABLES = KERNEL | 0x11;
    private static final int LOOP = KERNEL | 0x12;
    private static final int OPEN = KERNEL | 0x13;
    private static final int P = KERNEL | 0x14;
    private static final int PUTS = KERNEL | 0x15;
    private static final int PRINT = KERNEL | 0x16;
    private static final int PRINTF = KERNEL | 0x17;
    private static final int PROC = KERNEL | 0x18;
    private static final int RAISE = KERNEL | 0x19;
    private static final int RAND = KERNEL | 0x1a;
    private static final int READLINE = KERNEL | 0x1b;
    private static final int READLINES = KERNEL | 0x1c;
    private static final int REQUIRE = KERNEL | 0x1d;
    private static final int SCAN = KERNEL | 0x1e;
    private static final int SET_TRACE_FUNC = KERNEL | 0x1f;
    private static final int SINGLETON_METHOD_ADDED = KERNEL | 0x20;
    private static final int SLEEP = KERNEL | 0x21;
    private static final int SPLIT = KERNEL | 0x22;
    private static final int SPRINTF = KERNEL | 0x23;
    private static final int SRAND = KERNEL | 0x24;
    private static final int SUB = KERNEL | 0x25;
    private static final int SUB_BANG = KERNEL | 0x26;
    private static final int THROW = KERNEL | 0x27;

    // private static final int 

    /**
     * Constructor for Kernel.
     * @param runtime
     */
    public Kernel(Ruby runtime) {
        super(runtime);
    }

    /**
     * @see org.jruby.runtime.builtin.definitions.ModuleDefinition#createModule(Ruby)
     */
    protected RubyModule createModule(Ruby runtime) {
        return runtime.defineModule("Kernel");
    }

    /**
     * @see org.jruby.runtime.builtin.definitions.ModuleDefinition#defineModuleFunctions(ModuleFunctionsContext)
     */
    protected void defineModuleFunctions(ModuleFunctionsContext context) {
        context.createOptional("open", OPEN, 1);
        context.createOptional("format", SPRINTF, 1);
        context.createOptional("gets", GETS);
        context.createOptional("p", P);
        context.createOptional("print", PRINT);
        context.createOptional("printf", PRINTF);
        context.createOptional("puts", PUTS);
        context.createOptional("readline", READLINE);
        context.createOptional("readlines", READLINES);
        context.createOptional("sprintf", SPRINTF);
        context.createOptional("gsub!", GSUB_BANG);
        context.createOptional("gsub", GSUB);
        context.createOptional("sub!", SUB_BANG);
        context.createOptional("sub", SUB);

        context.create("chop!", CHOP_BANG, 0);
        context.create("chop", CHOP, 0);
        context.createOptional("chomp!", CHOMP_BANG);
        context.createOptional("chomp", CHOMP);
        context.createOptional("split", SPLIT);
        context.create("scan", SCAN, 1);
        context.createOptional("load", LOAD, 1);
        context.create("autoload", AUTOLOAD, 2);
        context.createOptional("raise", RAISE);
        context.create("require", REQUIRE, 1);
        context.create("global_variables", GLOBAL_VARIABLES, 0);
        context.create("local_variables", LOCAL_VARIABLES, 0);
        context.create("block_given?", BLOCK_GIVEN, 0);
        context.create("iterator?", BLOCK_GIVEN, 0);
        context.create("proc", PROC, 0);
        context.create("lambda", PROC, 0);
        context.create("loop", LOOP, 0);
        context.createOptional("eval", EVAL, 1);
        context.createOptional("caller", CALLER);
        context.create("catch", CATCH, 1);
        context.createOptional("throw", THROW, 1);
        context.create("singleton_method_added", SINGLETON_METHOD_ADDED, 1);
        context.create("set_trace_func", SET_TRACE_FUNC, 1);
        context.create("sleep", SLEEP, 1);
        context.create("`", BACKQUOTE, 1);
        context.createOptional("exit", EXIT);
        context.createOptional("srand", SRAND);
        context.createOptional("rand", RAND);
    }

    /**
     * @see org.jruby.runtime.IStaticCallable#callIndexed(int, IRubyObject, IRubyObject[])
     */
    public IRubyObject callIndexed(int index, IRubyObject receiver, IRubyObject[] args) {
        switch (index) {
            case AUTOLOAD :
                throw new NotImplementedError(receiver.getRuntime(), "not implemented");
            case BACKQUOTE :
                return KernelModule.backquote(receiver, args[0]);
            case BLOCK_GIVEN :
                return KernelModule.block_given(receiver);
            case CALLER :
                return KernelModule.caller(receiver, args);
            case CATCH :
                return KernelModule.rbCatch(receiver, args[0]);
            case CHOMP :
                return KernelModule.chomp(receiver, args);
            case CHOMP_BANG :
                return KernelModule.chomp_bang(receiver, args);
            case CHOP :
                return KernelModule.chop(receiver);
            case CHOP_BANG :
                return KernelModule.chop_bang(receiver);
            case EVAL :
                return KernelModule.eval(receiver, args);
            case EXIT :
                return KernelModule.exit(receiver, args);
            case GETS :
                return KernelModule.gets(receiver, args);
            case GLOBAL_VARIABLES :
                return KernelModule.global_variables(receiver);
            case GSUB :
                return KernelModule.gsub(receiver, args);
            case GSUB_BANG :
                return KernelModule.gsub_bang(receiver, args);
            case LOAD :
                return KernelModule.load(receiver, args);
            case LOCAL_VARIABLES :
                return KernelModule.local_variables(receiver);
            case LOOP :
                return KernelModule.loop(receiver);
            case OPEN :
                return KernelModule.open(receiver, args);
            case P :
                return KernelModule.p(receiver, args);
            case PRINT :
                return KernelModule.print(receiver, args);
            case PRINTF :
                return KernelModule.printf(receiver, args);
            case PROC :
                return KernelModule.proc(receiver);
            case PUTS :
                return KernelModule.puts(receiver, args);
            case RAISE :
                return KernelModule.raise(receiver, args);
            case RAND :
                return KernelModule.rand(receiver, args);
            case READLINE :
                return KernelModule.readline(receiver, args);
            case READLINES :
                return KernelModule.readlines(receiver, args);
            case REQUIRE :
                return KernelModule.require(receiver, args[0]);
            case SCAN :
                return KernelModule.scan(receiver, args[0]);
            case SET_TRACE_FUNC :
                return KernelModule.set_trace_func(receiver, args[0]);
            case SINGLETON_METHOD_ADDED :
                return receiver.getRuntime().getNil(); // FIXME
            case SLEEP :
                return KernelModule.sleep(receiver, args[0]);
            case SPLIT :
                return KernelModule.split(receiver, args);
            case SPRINTF :
                return KernelModule.sprintf(receiver, args);
            case SRAND :
                return KernelModule.srand(receiver, args);
            case SUB :
                return KernelModule.sub(receiver, args);
            case SUB_BANG :
                return KernelModule.sub_bang(receiver, args);
            case THROW :
                return KernelModule.rbThrow(receiver, args);
            default :
                Asserts.assertNotReached("'" + index + "' is not a valid index.");
                return null;
        }
    }
}