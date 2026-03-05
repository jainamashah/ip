# Jaanu User Guide

Jaanu is a **personal task assistant** that runs in your terminal.
It helps you keep track of:

- **Todos** – simple tasks with just a description  
- **Deadlines** – tasks that must be done **by** a certain date/time  
- **Events** – tasks that happen **from** one time **to** another  

It saves your tasks to a file (`jaanu.txt`), so your list is available the next time you run the app.

---

## 1. Quick Start

1. **Ensure you have Java 17 or later installed.**
2. **Compile the project** (from the project root):

   ```bash
   javac -cp src -d bin src/main/java/jaanu/ui/Jaanu.java
   ```

3. **Run Jaanu**:

   ```bash
   java -cp bin jaanu.ui.Jaanu
   ```

4. You should see a welcome message from Jaanu.  
   Type a command and press **Enter** to interact.
5. Type `bye` to exit the program.

All tasks are stored in `jaanu.txt` in the same folder as the program.

---

## 2. Overview of Commands

| Command format                               | What it does                                      |
|----------------------------------------------|---------------------------------------------------|
| `list`                                       | Shows all tasks in your list                     |
| `todo DESCRIPTION`                           | Adds a todo                                      |
| `deadline DESCRIPTION /by BY`                | Adds a deadline                                  |
| `event DESCRIPTION /from FROM /to TO`        | Adds an event                                    |
| `mark INDEX`                                 | Marks the task at `INDEX` as done               |
| `unmark INDEX`                               | Marks the task at `INDEX` as not done           |
| `delete INDEX`                               | Deletes the task at `INDEX`                     |
| `find KEYWORD`                               | Finds the first task whose description has word `KEYWORD` |
| `bye`                                        | Saves and exits                                  |

`INDEX` refers to the **1-based index** shown in the `list` output.

---

## 3. Features

### 3.1 Viewing all tasks: `list`

- **What it does**: Shows every task currently stored, with their index and status.
- **Format**: `list`

**Example input**

```text
list
```

**Example output (if you have some tasks)**

```text
1. [T][ ] buy flowers for jaanu
2. [D][X] submit assignment (by tonight)
3. [E][ ] date with jaanu (from 7pm to 11pm)
```

If your list is empty, Jaanu will complain and tell you to add something first.

---

### 3.2 Adding a todo: `todo`

- **What it does**: Adds a simple task with only a description.
- **Format**: `todo DESCRIPTION`

**Example input**

```text
todo buy flowers for jaanu
```

**Expected outcome**

- A new todo task is added to your list.
- Jaanu confirms the addition and shows how many tasks you now have.

---

### 3.3 Adding a deadline: `deadline`

- **What it does**: Adds a task that needs to be done by a certain date/time.
- **Format**: `deadline DESCRIPTION /by BY`

**Example input**

```text
deadline confess to jaanu /by tonight
```

**Expected outcome**

- A new deadline is added with description `confess to jaanu` and `by` time `tonight`.
- If you forget `/by` or the date/time, Jaanu will show an error and tell you the correct format.

---

### 3.4 Adding an event: `event`

- **What it does**: Adds a task that happens during a time interval.
- **Format**: `event DESCRIPTION /from FROM /to TO`

**Example input**

```text
event date with jaanu /from 7pm /to 11pm
```

**Expected outcome**

- A new event is added with the specified start and end times.
- If you miss `/from` or `/to`, Jaanu will show an error and remind you of the full format.

---

### 3.5 Marking a task as done: `mark`

- **What it does**: Marks a task as completed.
- **Format**: `mark INDEX`

**Example input**

```text
mark 2
```

**Expected outcome**

- Task number 2 is marked as done.
- Jaanu responds with a cheeky congratulation and shows the updated task.
- If the index is missing, not a number, or out of range, an error message is shown.

---

### 3.6 Marking a task as not done: `unmark`

- **What it does**: Marks a previously completed task as not done.
- **Format**: `unmark INDEX`

**Example input**

```text
unmark 2
```

**Expected outcome**

- Task number 2 is marked as not done.
- Jaanu gently (or not so gently) reminds you to go finish it.

---

### 3.7 Deleting a task: `delete`

- **What it does**: Removes a task from your list.
- **Format**: `delete INDEX`

**Example input**

```text
delete 3
```

**Expected outcome**

- Task number 3 is removed from the list.
- Jaanu shows which task was deleted and how many tasks remain.

---

### 3.8 Finding a task by keyword: `find`

- **What it does**: Finds the **first** task whose description contains the exact word `KEYWORD`.
- **Format**: `find KEYWORD`

**Example input**

```text
find jaanu
```

**Expected outcome**

- Jaanu shows the first matching task (its index and full details).  
- If no task contains that word, Jaanu tells you there is nothing there.

---

### 3.9 Exiting the program: `bye`

- **What it does**: Saves your current tasks and exits.
- **Format**: `bye`

**Example input**

```text
bye
```

**Expected outcome**

- Jaanu says goodbye and the program terminates.
- Your tasks remain saved in `jaanu.txt` for the next run.

---

## 4. Data Storage

- Jaanu stores all tasks in a plain text file named `jaanu.txt`.
- The file is loaded when the app starts and saved after each command.
- If the file cannot be loaded, Jaanu will start with an empty list and show an error message.

You normally do **not** need to manage this file manually.

---

## 5. Error Messages & Personality

Jaanu has a playful, slightly savage personality.
When something goes wrong (missing arguments, invalid indexes, wrong formats), you will see:

- A clearly formatted error message
- A suggestion for the correct command format

Read the error text carefully – it almost always tells you exactly how to fix the issue.
