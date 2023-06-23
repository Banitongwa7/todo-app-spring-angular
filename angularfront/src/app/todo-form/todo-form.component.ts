import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.css']
})
export class TodoFormComponent implements OnInit {
  newTodo: any = {
    title: '',
    description: ''
  };

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  addTodo(): void {
    console.log(this.newTodo);

    // Ajouter une tâche
    this.http.post<any>('http://localhost:8080/api/todos', this.newTodo).subscribe(
      todo => {
        console.log('Tâche ajoutée avec succès', todo);
        // Réinitialiser le formulaire
        this.newTodo = {};
      },
      error => console.log(error)
    );
  }
}
