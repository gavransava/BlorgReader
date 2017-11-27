package savagavran.blorgreader.main.blogList.di;

import dagger.Component;
import savagavran.blorgreader.di.AppComponent;
import savagavran.blorgreader.main.blogList.view.BlogsFragment;

@BlogsScope
@Component(dependencies = {AppComponent.class}, modules = {BlogsModule.class})
public interface BlogsComponent {

    void inject(BlogsFragment blogsFragment);

}
