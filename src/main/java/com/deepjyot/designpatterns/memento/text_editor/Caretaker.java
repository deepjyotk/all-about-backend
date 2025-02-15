package com.deepjyot.designpatterns.memento.text_editor;

import java.util.Stack;

//Caretaker Class: Manage mementos (snapshots of the TextEditor state)
public class Caretaker {
    private final Stack<EditorMemento> history = new Stack<>();

    public void saveState(TextEditor editor){
        history.push(editor.save());
    }
    public void undo(TextEditor editor){
        if(!history.empty()){
            history.pop();
            editor.restore(history.peek());
        }
    }
}