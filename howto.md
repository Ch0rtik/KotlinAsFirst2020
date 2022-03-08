git clone https://github.com/Ch0rtik/KotlinAsFirst2020.git <br>
cd KotlinAsFirst2020 <br>
<br>
git branch backport <br>
git checkout backport <br>
git remote add upstream-my https://www.github.com/Ch0rtik/KotlinAsFirst2021.git <br>
git fetch upstream-my <br>
git cherry-pick "510a7dd^..3276617" (перенесены только коммиты с моими решениями за 1 семестр) <br>
<br>
git checkout master <br>
git merge upstream-my/master <br>
git remote add upstream-theirs https://github.com/AnadolStudio/KotlinAsFirst.git <br>
git fetch upstream-theirs <br>
git merge -s ours upstream-theirs/master (самое простое решение конфликтов)<br>
<br>
git remote -v > remotes <br>
git add remotes <br>
git commit -m "Remotes" <br>
git add howto <br>
git commit -m "Howto" <br>
git push
<br>
git checkout backport <br>
git push -u origin backport 