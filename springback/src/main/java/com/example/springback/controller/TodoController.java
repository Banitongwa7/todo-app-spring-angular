package com.example.springback.controller;


import com.example.springback.entities.Todo;
import com.example.springback.repos.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            return ResponseEntity.ok(todo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        if (!todoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        todo.setId(id);
        Todo updatedTodo = todoRepository.save(todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (!todoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
