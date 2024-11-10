# To-Do List Application

This is a Java-based To-Do List application featuring a calendar interface for organizing tasks. It allows users to view, add, edit, and delete tasks based on the date. The project uses **JCalendar** for calendar functionality, supporting both a **fake database** (using an in-memory list) and **Firebase** as a real database option for task storage.

## Features

- **Dual Database Support**: 
  - **Fake Database**: Uses an in-memory list to store tasks.
  - **Firebase Database**: Connects to Firebase to store tasks persistently.
- **Calendar Navigation**: Easily switch between months and years.
- **Task Priority Indicators**: Days with tasks are highlighted with colors corresponding to task priority levels.
- **Tooltip Display**: Hovering over a highlighted day shows a tooltip with task details, including the task name and time.
- **Quick Task Addition**: Left-click on a day to quickly add a new task.
- **Context Menu on Right-Click**:
  - If tasks are scheduled for the day, options to **Add**, **Edit**, or **Delete** tasks are available.
  - If no tasks are scheduled, the menu will only show the **Add** option.
  - When multiple tasks are scheduled for a day, selecting **Edit** or **Delete** opens a submenu to choose which task to modify.
