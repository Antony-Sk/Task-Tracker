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
        <div class="card-header">Task</div>
        <table class="table">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Priority</th>
                <th>Status</th>
                <th></th>
            </tr>
            <tr>
                <th><a href="/project/${projectId}/task/${task.id}">${task.taskName}</a></th>
                <th>
                    <pre>${task.description}</pre>
                </th>
                <th>${task.priority}</th>
                <th>${task.status.toString()}</th>
                <th>
                    <form method="post" action="/project/${projectId}/task/${task.id}/delete"><input type="submit"
                                                                               value="Delete task">
                    </form>
                </th>
            </tr>
            <tr>
                <form action="/project/${projectId}/task/${task.id}/edit" method="post">
                    <th><label for="taskName"></label><input required name="taskName" id="taskName"
                                                             value="${task.taskName}"></th>
                    <th><label for="description"></label><textarea required name="description"
                                                                   id="description">${task.description}</textarea></th>
                    <th><label for="priority"></label><input min="-999999999" max="999999999" required name="priority" id="priority" type="number"
                                                             value="${task.priority}"></th>
                    <th>
                        <select autofocus required id="status" name="status">
                            <option <#if task.status.toString()=='TO_DO'>selected="selected"
                                    </#if> value="TO_DO">To do
                            </option>
                            <option <#if task.status.toString()=='IN_PROGRESS'>selected="selected"
                                    </#if>value="IN_PROGRESS">In progress
                            </option>
                            <option <#if task.status.toString()=='DONE'>selected="selected"
                                    </#if>value="DONE">Done
                            </option>
                        </select>
                    </th>
                    <th><input type="submit" value="Update"></th>
                </form>
            </tr>
        </table>
    </div>
</div>

</body>