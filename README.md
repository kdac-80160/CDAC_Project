# CDAC_Project
First you have to clone the repository.
=> git clone https://github.com/kdac-80160/CDAC_Project.git

Then open your terminal and create a new branch
=> git checkout -b branch_name   // branch_name may be represented as your name, for instance - mahendra

Everything you have to do in your branch, git add and git commit commands has to be there in your branch only.
...... your work
=> git add .
=> git commit -m "your commit"

Once you are done and ready to push the data then... First switch to the main branch
=> git checkout main

Then pull the data so that if anybody would have updated something new on the main branch, you will be having that data and merge your work into it.
=> git pull

Now merge your data with the main branch
=> git merge <branch_name>

Finally you can push your data to the main branch
=> git push
