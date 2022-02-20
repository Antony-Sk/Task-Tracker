<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Task Tracker</title>
    <link href="<@spring.url '/css/bootstrap.min.css'/>" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="card">
        <div class="card-header">Edit Project</div>
        <div class="card-body">
            <form method="post" action="/project/${projectId}">
                <table class="table">
                    <tr>
                        <th>Name</th>
                        <th>Start Date</th>
                        <th>Completion Date</th>
                        <th>Status</th>
                        <th>Priority</th>
                    </tr>
                    <tr>
                        <th>
                            <input autofocus id="name" name="name" value="${project.name}" required>
                        </th>
                        <th>
                            <input autofocus id="startDate" name="startDate" type="datetime-local"
                                   value="${project.startDate}" required>
                        </th>
                        <th>
                            <input autofocus id="completionDate" name="completionDate" type="datetime-local"
                                   value="${project.completionDate}" required>
                        </th>
                        <th>
                            <select autofocus required id="status" name="status">
                                <option <#if project.status=='ACTIVE'>selected="selected"
                                        </#if> value="ACTIVE">active
                                </option>
                                <option <#if project.status=='COMPLETED'>selected="selected"
                                        </#if>value="COMPLETED">completed
                                </option>
                                <option <#if project.status=='NOT_STARTED'>selected="selected"
                                        </#if>value="NOT_STARTED">not started
                                </option>
                            </select>
                        </th>
                        <th>
                            <input required autofocus id="priority" min="-999999999" max="999999999" name="priority" type="number"
                                   value="${project.priority}">
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <input type="submit" value="Update">
                        </th>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="card">
        <div class="card-header">Tasks of the project ${project.name}</div>
        <div class="card-body">
            <table class="table">
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Priority</th>
                </tr>
                <#list tasks as task>
                    <tr>
                        <th><a href="/project/${projectId}/task/${task.id}">${task.taskName}</a></th>
                        <th>${task.description}</th>
                        <th>${task.priority}</th>
                        <th>${task.status.toString()}</th>
                        <th>
                            <form method="post" action="/project/${projectId}/task/${task.id}/delete"><input type="submit"
                                                                                                             value="Delete task from project">
                            </form>
                        </th>
                    </tr>
                </#list>
                <tr>
                    <form action="/project/${projectId}/createTask" method="post">
                        <th><label for="taskName"></label><input required name="taskName" id="taskName"></th>
                        <th><label for="description"></label><input required name="description" id="description"></th>
                        <th><label for="priority"></label><input min="-999999999" max="999999999" required name="priority" id="priority" type="number"></th>
                        <th>
                            <select autofocus required id="status" name="status">
                                <option value="TO_DO" selected="selected">To do</option>
                                <option value="IN_PROGRESS">In progress</option>
                                <option value="DONE">Done</option>
                            </select>
                        </th>
                        <th><input type="submit" value="Create new task"></th>
                    </form>
                </tr>
            </table>
        </div>
    </div>
    <div class="card-body">
        <form method="post" action="/delete/${projectId}">
            <input type="submit" style="background-color: red" value="Delete project!!!">
        </form>
    </div>
</div>
</body>