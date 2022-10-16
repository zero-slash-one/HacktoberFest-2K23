from flask import Flask,render_template,request,redirect
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = "sqlite:///todo.db"
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app) 

class Todo(db.Model):
    item = db.Column(db.Integer,primary_key=True)
    title = db.Column(db.String(200), nullable=False)
    desc = db.Column(db.String(500), nullable=False)
    date_created = db.Column(db.DateTime, default=datetime.utcnow)

    def __repr__(self) -> str:
        return f"{self.item} - {self.title}"
    

@app.route("/",methods=["GET", "POST"])
def home():
    if request.method=="POST":
        title = request.form['title']
        desc = request.form['desc']
        todo = Todo(title=title, desc=desc)
        db.session.add(todo)
        db.session.commit()
    allTodo = Todo.query.all()
    return render_template('index.html',allTodo=allTodo)

@app.route('/update/<int:item>',methods=["GET", "POST"])
def update(item):
    if request.method=="POST":
        title = request.form['title']
        desc = request.form['desc']
        todo = Todo.query.filter_by(item=item).first()
        todo.title = title
        todo.desc = desc
        db.session.add(todo)
        db.session.commit()
        return redirect('/')
    todo = Todo.query.filter_by(item=item).first()
    return render_template('update.html',todo=todo)
    
@app.route('/delete/<int:item>')
def delete(item):
    todo = Todo.query.filter_by(item=item).first()
    db.session.delete(todo)
    db.session.commit()
    return redirect('/')

    

if __name__ == "__main__":
    app.run(debug=True)
