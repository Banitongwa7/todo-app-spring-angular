import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {
  todos: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
      this.getTodos();
  }

  getTodos(): void {
    this.http.get<any[]>('http://localhost:8080/api/todos').subscribe(
      todos => this.todos = todos,
      error => console.log(error)
    )
  }

  deleteTodo(id: number): void {
    this.http.delete(`http://localhost:8080/api/todos/${id}`).subscribe(
      () => {
        console.log('Tâche supprimée avec succès');
        this.getTodos();
      },
      error => console.log(error)
    );
  }

}
