package org.builddoctor.hudson.plugins.sound;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;

import java.io.IOException;

import org.builddoctor.hudson.plugins.sound.util.SoundLogger;
import org.kohsuke.stapler.DataBoundConstructor;

public class SoundPublisher extends Recorder{

    protected boolean canContinue(final Result result) {
        return result != Result.ABORTED;
    }
	
    @DataBoundConstructor
    public SoundPublisher(){    	
    }
	
    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {

        if (this.canContinue(build.getResult())) {
	    	
        	if (build.getResult()==Result.SUCCESS){
	    		SoundLogger.log(listener, "SUCCESS");    			    	
	    	}
	    	
	    	else if (build.getResult()==Result.UNSTABLE){
	    		SoundLogger.log(listener, "UNSTABLE");    		
	    	}
	    	
	    	else if (build.getResult()==Result.FAILURE){
	    		SoundLogger.log(listener, "FAILURE");    		
	    	}    	
        }
    	return true;
    }

	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.NONE;
	}
	
	@Extension
	public static class SoundDescriptor extends BuildStepDescriptor<Publisher> {

		public SoundDescriptor() {
			super(SoundPublisher.class);
		}

		@Override
		public String getDisplayName() {
			return "Activate the sound plugin";
		}

		@Override
		public boolean isApplicable(Class<? extends AbstractProject> clazz) {
			return true;
		}
	}

	
}

