# CS61B-learn
# 这是记录对CS61B作业提交而创建的代码，用于GradeScoop评分系统  
  
下面是一些git命令的使用  

拉取远程仓库  

- 克隆别人的仓库到本地  `git remote add <repo> <url>`  `git pull <repo> <tag>` 将远超地址和本地的`repo`绑定，这时的`repo`为一个仓库，然后命令2就将远程的仓库的`tag`分支的文件拉取过来  

- 绑定本地的仓库和远程的仓库 `git remote add <repo> <url>`  

- 声明当前目录可以`git`控制 `git init`  

- 将当前目录添加到仓库中 `git .`  

- 上传到程仓库并附带信息 `git commit -m "<说明>" 说明请写正在进行的lab\prj    

- 将本地`repo`仓库的`tag`分支和远程仓库同步 `git push <repo> <tag>` 

这里是环境的配置参考 ![here](https://blog.csdn.net/O_ra_Cle/article/details/133961760)  

注意Gradescope的密码长度不小于12！


Attention:
1. lab1中一开始其实是没有`numberNext()`函数的，按照时间线后lab4中添加了`numberNext()`函数用于git合并处理实验。所以lab4的git实验时，手动将其回退到修改前就相当于lab4中的step1了  

2. 
