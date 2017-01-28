package com.boardgame.service.script;

import com.boardgame.service.board.BoardService;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class ScriptService {

    @Autowired
    protected BoardService boardService;


    public void runScript(String script, Map<String, Object> args) throws IOException {

        if(args == null){
            args = new HashMap<>();
        }

        args.put("boardService", boardService);

        IOException _e = null;
        // Create and enter a Context. A Context stores information about the execution environment of a script.
        Context cx = Context.enter();
        //enter();
        try {
            // Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
            // executed. The null parameter tells initStandardObjects
            // to create and return a scope object that we use
            // in later calls.
            Scriptable scope = cx.initStandardObjects();

            // Pass the Stock Java object to the JavaScript context
            Iterator<Map.Entry<String, Object>> iterator = args.entrySet().iterator();
            for (int i = 0; iterator.hasNext(); i++) {

                Map.Entry<String, Object> entry = iterator.next();
                Object wrappedObj = Context.javaToJS(entry.getValue(), scope);
                ScriptableObject.putProperty(scope, entry.getKey(), wrappedObj);
            }

            // Execute the script
            cx.evaluateString(scope, script, script, 1, null);
        } catch (Exception e) {
            _e = new IOException(e);
        } finally {
            // Exit the Context. This removes the association between the Context and the current thread and is an
            // essential cleanup action. There should be a call to exit for every call to enter.
            Context.exit();
        }

        if (_e != null) {
            throw _e;
        }
    }
}
